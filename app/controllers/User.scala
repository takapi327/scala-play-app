package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent._
import lib.model._
import lib.persistence._
import play.api.data.Form
import play.api.data.Forms._

// CSRF対策
import play.filters.csrf.CSRF
import play.api.i18n.I18nSupport

import model._

@Singleton
class UserController @Inject()(
  userRepo:   UserRepository,
  passRepo:   UserPassRepository,
  cc:         MessagesControllerComponents
)(implicit ec: ExecutionContext) 
  extends MessagesAbstractController(cc){

  def index() = Action.async {implicit request =>
    for {
      userSeq <- userRepo.getAll() 
    } yield {
      val vv = ViewValueUserList(
        title = "User一覧",
        cssSrc = Seq("main.css"),
        jsSrc  = Seq("main.js"),
        data   = userSeq
      )
      Ok(views.html.site.user.List(vv))
    }
  }

  def showSignupForm() = Action {implicit request =>
    Ok(views.html.site.user.Add(new ViewValueUserAdd))
  }

  def signup() = Action.async {implicit request =>
    StatusValue.signupForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.site.user.Add(new ViewValueUserAdd)))
      },
      userForm => {
        val pass = userForm.password
        for {
          userData <- userRepo.add(userForm.name, userForm.mail)
          userId   <- userRepo.filterByMail(userForm.mail)
          _        <- passRepo.add(userId.head.id, pass)
        } yield {
          Redirect(routes.HomeController.index).withCookies(Cookie("user", userForm.name)).bakeCookies()
        }
      }
    )
  }
}
