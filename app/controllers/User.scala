package controllers

import lib.model._
import lib.persistence._
import model._
import auth._

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
  userRepo: UserRepository,
  passRepo: UserPassRepository,
  authRepo: AuthTokenRepository,
  cc:       MessagesControllerComponents
)(implicit ec: ExecutionContext) 
  extends MessagesAbstractController(cc){

  def index() = Action.async {implicit request =>
    val userToken = request.cookies.get("user").map(_.value).getOrElse("Not-Found-User")
    val getUserId = authRepo.filterByToken(userToken)
    for {
      userTokenId <- getUserId
      userId      =  userTokenId.map(x => x.userId.get)
      userDetail  <- userId.map(u => userRepo.filterById(u)).get
    } yield {
      Ok(views.html.site.user.List(ViewValueUserList(user = userDetail)))
    }
  }

  def showSignupForm() = Action {implicit request =>
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
            case true  => passRepo.add(Some(userDate), pass)
            case false => Future.successful(0)
          }
          
          result <- mailFil match {
            case false => Future.successful(NotFound(views.html.error.page404(new ViewValueError)))
            case true  =>
              val token: String = TokenGenerator().next(30)
              val newCookie     = Cookie("user", token)
              authRepo.add(Some(userDate), token)
              Future.successful(Redirect(routes.UserController.index).withCookies(newCookie))
          }
          
        } yield {
          result
        }
      }
    )
  }

  def showLoginForm() = Action {implicit request =>
    Ok(views.html.site.user.Login(new ViewValueUserLogin))
  }

  def login() = Action.async {implicit request =>
    StatusValue.loginForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest(views.html.site.user.Login(new ViewValueUserLogin(form = errorForm))))
      },
      loginSucces => {
        val pass = loginSucces.password
        val mail = loginSucces.mail
        for {
          mailUser <- userRepo.filterByMail(mail)
          passUser <- passRepo.filterByPass(pass)
        } yield {
          println(mailUser)
          println(passUser)
          val userMailId = mailUser.map(x => x.id.get)
          val userPassId = passUser.map(y => y.user_id.get).get
          val newToken = userMailId match {
            case Some(id) if id == userPassId => TokenGenerator().next(30)
            case None                         => "NotFound"
          }
          println(newToken)
          Ok(views.html.site.user.Login(new ViewValueUserLogin))
        }
      }
    )
  }
}
