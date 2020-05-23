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
/*
  def index() = Action {implicit request =>
    
    for {
      userSeq <- userRepo.getAll() 
    } yield {
      
     val cookie = request.cookies.get("user")
      val vv = ViewValueUserList(
        title = "User一覧",
        cssSrc = Seq("main.css"),
        jsSrc  = Seq("main.js")
        //data   = userSeq
        //cookie = cookie.getOrElse(Cookie("user", "no-cookie."))
      )
      Ok(views.html.site.user.List(vv))
  //  }
  }
*/
  def showSignupForm() = Action {implicit request =>
    Ok(views.html.site.user.Add(new ViewValueUserAdd))
  }

  def signup() = Action.async {implicit request =>
    StatusValue.signupForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest(views.html.site.user.Add(new ViewValueUserAdd(form = errorForm))))
      },
      userForm => {
        val pass = userForm.password
        for {

          mailFil <- userRepo.filterByMail(userForm.mail)
            .map(user => user match {
              case Some(_) => false
              case None    => true
            })

          userDate <- mailFil match {
            case true  => userRepo.add(userForm.name, userForm.mail)
            case false => Future.successful(0L)
          }

          _  <- mailFil match {
            case true  => passRepo.add(Some(userDate), pass)
            case false => Future.successful(0)
          }

          result <- mailFil match {
            case false => Future.successful(NotFound(views.html.error.page404(new ViewValueError)))
            case true  => 
              val cookie = request.cookies.get("user").map(_.value).getOrElse("Nothing.")
              Future.successful(Ok(views.html.site.user.List(new ViewValueUserList)).withCookies(Cookie("user", cookie)).bakeCookies())
          }
          
        } yield {
          result
          //val x = userRepo.signup(userId.head.id, userForm.name, userForm.mail)
          //val token = CSRF.getToken(request)
        }
      }
    )
  }
}
