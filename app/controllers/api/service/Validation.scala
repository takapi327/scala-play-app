package controllers.api.service

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsUser
import json.writes.JsValueWritesIsAuth

import play.api.mvc._
import play.api.libs.json._
import play.api.i18n.I18nSupport
import play.api.libs.json.JsPath.json

class ValidationController @Inject()(
  cc: MessagesControllerComponents
)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc)
  with    I18nSupport {

  def isAuthenticate() = Action {implicit request =>
    val cookies = request.cookies.get("My-Xsrf-Cookie")
    val jsWritesAuth = JsValueWritesIsAuth(
      isAuth = cookies match {
        case Some(_) => true
        case None    => false
      }
    )
    cookies match {
      case Some(_) => Ok(Json.toJson(jsWritesAuth))
      case None    => BadRequest
    }
  }

  def emailValidation() = Action(parse.json) async {implicit request =>
    ???
  }
}
