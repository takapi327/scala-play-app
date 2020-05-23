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
        Future.successful(Ok(views.html.site.user.Add(new ViewValueUserAdd)))
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
            // ↓処理が死んでるので後で修正
            case false => userRepo.add(userForm.name, userForm.mail)
          }
          _      <- mailFil match {
            case true  => passRepo.add(Some(userDate), pass)
            // ↓処理が死んでるので後で修正
            case false => Future.successful(NotFound(views.html.error.page404(new ViewValueError)))
          }
        } yield {
          //val x = userRepo.signup(userId.head.id, userForm.name, userForm.mail)
          val cookie = request.cookies.get("user").map(_.value).getOrElse("Nothing.")
          val token = CSRF.getToken(request)
          val vv = ViewValueUserList(
            title  = "User一覧",
            cssSrc = Seq("main.css"),
            jsSrc  = Seq("main.js"),
            //cookie = cookie,
           // either = x
          )
        Ok(views.html.site.user.List(vv)).withCookies(Cookie("user", cookie)).bakeCookies() 
        }
      }
    )
  }
}
