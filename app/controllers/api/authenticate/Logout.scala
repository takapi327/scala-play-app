package controllers.api.authenticate

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsUser

import lib.persistence.AuthTokenRepository

import auth.TokenGenerator

import play.api.mvc._
import play.api.libs.json._
import play.api.i18n.I18nSupport
import play.api.libs.json.JsPath.json

class UserLogoutController @Inject()(
  authRepo: AuthTokenRepository,
  cc:       MessagesControllerComponents
)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc)
  with    I18nSupport {

   def logout() = Action.async {implicit request =>
     val userCookies = request.cookies.get("My-Xsrf-Cookie").map(_.value)
     userCookies match {
       case None        => Future.successful(BadRequest)
       case Some(token) => {
         for {
           _ <- authRepo.deleteToken(token)
         } yield {
           Ok.discardingCookies(DiscardingCookie("My-Xsrf-Cookie"))
         }
       }
     }
   }
}
