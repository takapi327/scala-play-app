package controllers.api.authenticate

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsUser
import json.writes.JsValueWritesUser

import lib.persistence.{UserRepository, UserPassRepository, AuthTokenRepository}
import lib.model.User

import auth.TokenGenerator

import play.api.mvc._
import play.api.libs.json._
import play.api.i18n.I18nSupport
import play.api.libs.json.JsPath.json

class UserLoginController @Inject()(
  userRepo: UserRepository,
  passRepo: UserPassRepository,
  authRepo: AuthTokenRepository,
  cc:       MessagesControllerComponents
)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc)
  with    I18nSupport {

    def login() = Action(parse.json) async {implicit request =>
      val json   = request.body
      val result = json.validate[JsValueReadsUser]
      val user   = result.get
      for {
        mailUser <- userRepo.filterByMail(user.email)
        passUser <- passRepo.filterByPass(user.password)
      } yield {
        val userMailId = mailUser.map(x => x.id.get)
        val userPassId = passUser.map(y => y.userId).getOrElse(0)
        val newToken   =
          userMailId match {
            case Some(id) if id == userPassId || userPassId != 0 => Some(TokenGenerator().next(30))
            case Some(_)  if userPassId == 0                     => None
            case None                                            => None
          }
        newToken match {
          case None    => authRepo.updateToken(None, None)
          case Some(_) => authRepo.updateToken(newToken, userMailId)
        }
        val newCookie    = Cookie("My-Xsrf-Cookie", newToken.getOrElse("No-Cookie"), domain = Some("localhost"))
        val jsWritesAuth = JsValueWritesUser.toWrites(
          email    = user.email,
          password = user.password
        )
        newToken match {
          case None    => BadRequest
          case Some(_) => Ok(Json.toJson(jsWritesAuth)).withCookies(newCookie)
        }
      }
    }
  }
