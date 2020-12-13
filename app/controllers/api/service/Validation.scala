package controllers.api.service

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsEmail
import json.writes.{JsValueWritesIsAuth, JsValueWritesEmailValid}

import cats.data.EitherT
import cats.implicits._

import play.api.mvc._
import play.api.libs.json._
import play.api.i18n.I18nSupport
import play.api.libs.json.JsPath.json

import lib.persistence.UserRepository

class ValidationController @Inject()(
  uerRepo: UserRepository,
  cc:      MessagesControllerComponents
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

  def isEmailRegistered() = Action(parse.json) async {implicit request =>
    val jsonEmail = request.body.validate[JsValueReadsEmail].get
    for {
      email <- uerRepo.filterByMail(jsonEmail.email)
    } yield {
      email match {
        case Some(_) => Ok(Json.toJson(JsValueWritesEmailValid(isRegistered = true)))
        case None    => Ok(Json.toJson(JsValueWritesEmailValid(isRegistered = false)))
      }
    }
  }
}
