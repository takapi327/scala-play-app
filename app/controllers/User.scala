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
  userRepo: UserRepository,
  cc: MessagesControllerComponents
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

  def showAddForm() = Action {implicit request =>
    Ok(views.html.site.user.Add(new ViewValueUserAdd))
  }

  def create() = Action {implicit request =>
    val vv = ViewValueHome(
      title  = "Home",
      cssSrc = Seq("main.css"),
      jsSrc  = Seq("main.js")
    )
    Ok(views.html.site.index(vv)) 
  }
}
