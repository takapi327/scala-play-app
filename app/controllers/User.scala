package controllers

import lib.model._
import lib.persistence._
import model._
import auth._
import action.auth.{AuthAction, IsAlreadyLoginAction}

import scala.concurrent._
import javax.inject._

import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
// CSRF対策
import play.filters.csrf.CSRF
import play.api.i18n.I18nSupport


@Singleton
class UserController @Inject()(
  userRepo:             UserRepository,
  passRepo:             UserPassRepository,
  authRepo:             AuthTokenRepository,
  AuthAction:           AuthAction,
  IsAlreadyLoginAction: IsAlreadyLoginAction,
  cc:                   MessagesControllerComponents
)(implicit ec: ExecutionContext) 
  extends MessagesAbstractController(cc) with I18nSupport {

  def index() = AuthAction.async {implicit request =>
    Future(Ok(views.html.site.user.List(ViewValueUserList(user = request.user))))
  }

  def showSignupForm() = IsAlreadyLoginAction {implicit request =>
    Ok(views.html.site.user.Add(new ViewValueUserAdd))
  }

  def signup() = Action.async {implicit request =>
    StatusValue.signupForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest(views.html.site.user.Add(new ViewValueUserAdd(form = errorForm))))
      },
      userForm => {
        val pass     = userForm.password
        val userMail = userRepo.filterByMail(userForm.mail)
        val uName    = userForm.name
        for {
          /* メールが重複していないかどうか */
          mailFil <- userMail.map(user =>
            user match {
              case Some(_) => false
              case None    => true
            })

          /* user登録 */
          userDate <- mailFil match {
            case true  => userRepo.add(uName, userForm.mail)
            case false => Future.successful(0L)
          }

          /* password登録 */
          _  <- mailFil match {
            case true  => passRepo.add(userDate, pass)
            case false => Future.successful(0)
          }
          
          result <- mailFil match {
            case false => Future.successful(NotFound(views.html.error.page404(new ViewValueError)))
            case true  =>
              val token: String = TokenGenerator().next(30)
              val newCookie     = Cookie("user", token)
              authRepo.add(Some(userDate), Some(token))
              Future.successful(Redirect(routes.UserController.index).withCookies(newCookie))
          }
          
        } yield {
          result
        }
      }
    )
  }

  def showLoginForm() = IsAlreadyLoginAction {implicit request =>
    Ok(views.html.site.user.Login(new ViewValueUserLogin))
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
        val pass = loginSucces.password
        val mail = loginSucces.mail
        for {
          mailUser <- userRepo.filterByMail(mail)
          passUser <- passRepo.filterByPass(pass)
        } yield {
          val userMailId = mailUser.map(x => x.id.get)
          val userPassId = passUser.map(y => y.user_id).getOrElse(0)
          val newToken   = 
            userMailId match {
              case Some(id) if id == userPassId || userPassId != 0 => Some(TokenGenerator().next(30))
              case Some(_)  if userPassId == 0                     => None
              case None                                            => None
            }
          
          newToken match {
            case Some(_) => authRepo.updateToken(userMailId, newToken)
            case None    => authRepo.updateToken(userMailId, None)
          }
          val newCookie = Cookie("user", newToken.getOrElse("No-Cookie"))
          newToken match {
            case Some(_) => Redirect(routes.UserController.index).withCookies(newCookie)
            case None    => BadRequest(views.html.site.user.Login(new ViewValueUserLogin))
          }
        }
      }
    )
  }

   def logout() = Action.async {implicit request =>
     val userCookies = request.cookies.get("user").map(_.value)
     userCookies match {
       case None        => Future.successful(BadRequest(views.html.site.index(new ViewValueHome)))
       case Some(token) => {
         for {
           _ <- authRepo.deleteToken(token)
         } yield {
           Redirect(routes.HomeController.index).discardingCookies(DiscardingCookie("user"))
         }
       }
     }
   }
}
