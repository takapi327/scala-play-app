package controllers

import lib.model.{User, UserPassword => Pass}
import lib.persistence._
import model._
import auth._
import auth.{AuthActionHelpers, IsAlreadyLoginAction}
import services.AuthActionService

import scala.concurrent._
import javax.inject._

import cats.data.EitherT
import cats.implicits._

import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
// CSRF対策
import play.filters.csrf.CSRF
import play.api.i18n.I18nSupport

@Singleton
class UserController @Inject()(
  userRepo:    UserRepository,
  passRepo:    UserPassRepository,
  authRepo:    AuthTokenRepository,
  authService: AuthActionService,
  cc:          MessagesControllerComponents
)(implicit ec: ExecutionContext) 
  extends MessagesAbstractController(cc)
  with    I18nSupport
  with    AuthActionHelpers {

  def index() = AuthAction(authService.authenticate).async {implicit request =>
    request.attrs.get(Attr.KEY).map(_.user) match {
      case Some(user) => Future(Ok(views.html.site.user.List(ViewValueUserList(user = user))))
      case None       => Future(BadRequest(views.html.site.index(new ViewValueHome)))
    }
  }

  def showSignupForm() = AuthAction(authService.authenticate).async {implicit request =>
    request.attrs.get(Attr.KEY).map(_.user) match {
      case None    => Future(Ok(views.html.site.user.Add(new ViewValueUserAdd)))
      case Some(_) => Future(BadRequest(views.html.site.index(new ViewValueHome)))
    }
  }

  def signup() = Action.async {implicit request =>
    StatusValue.signupForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest(views.html.site.user.Add(new ViewValueUserAdd(form = errorForm))))
      },
      userForm => {
        val withNoIdUser = User.buildEntity(
          userForm.firstName,
          userForm.lastName,
          userForm.email
        )
        val hashPass = Pass.hash(userForm.password)
        val newToken = TokenGenerator().next(30)
        val result: EitherT[Future, Result, Result] =
          for {
            isMailRegistered <- EitherT(userRepo.filterByMail(userForm.email).map(_.toRight(BadRequest("Aleady Registered Email"))))
            newUser          <- EitherT(userRepo.add(withNoIdUser).map(Either.right(_)))
            newPass          <- EitherT(passRepo.add(newUser, hashPass).map(Either.right((_))))
            _                <- EitherT(authRepo.add(Some(newToken), Some(newUser)).map(Either.right(_)))
          } yield {
            isMailRegistered match {
              case Right => BadRequest
              case Left  => Redirect(routes.UserController.index).withCookies(Cookie("My-Xsrf-Cookie", newToken))
            }
          }
          result.value.map{
            case Right(successful) => successful
            case Left(badrequest)  => badrequest
          }
      }
    )
  }

  def showLoginForm() = AuthAction(authService.authenticate) async {implicit request =>
    request.attrs.get(Attr.KEY).map(_.user) match {
      case Some(_) => Future(BadRequest(views.html.site.index(new ViewValueHome)))
      case None    => Future(Ok(views.html.site.user.Login(new ViewValueUserLogin)))
    }
  }

  def login() = Action.async {implicit request =>
    StatusValue.loginForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.site.user.Login(
            new ViewValueUserLogin(form = errorForm)
          ))
        )
      },
      loginSucces => {
        val result: EitherT[Future, Result, Result] =
          for {
            mailUser <- EitherT(userRepo.filterByMail(loginSucces.email).map(_.toRight(NotFound("NotFound User"))))
            passUser <- EitherT(passRepo.filterById(mailUser.id.get).map(_.toRight(NotFound("NotFound User"))))
          } yield {
            val newToken    = TokenGenerator().next(30)
            val updateToken = Pass.verify(loginSucces.password, passUser.hash) match {
              case true  => Some(authRepo.updateToken(Some(newToken), mailUser.id))
              case false => None
            }
            updateToken match {
              case None    => BadRequest
              case Some(_) => Redirect(
                routes.UserController.index
              ).withCookies(Cookie("My-Xsrf-Cookie", newToken))
            }
          }
        result.value.map{
          case Right(successful) => successful
          case Left(badrequest)  => badrequest
        }
      }
    )
  }

   def logout() = Action.async {implicit request =>
     val userCookies = request.cookies.get("My-Xsrf-Cookie").map(_.value)
     userCookies match {
       case None        => Future.successful(BadRequest(views.html.site.index(new ViewValueHome)))
       case Some(token) => {
         for {
           _ <- authRepo.deleteToken(token)
         } yield {
           Redirect(routes.HomeController.index).discardingCookies(DiscardingCookie("My-Xsrf-Cookie"))
         }
       }
     }
   }
}
