package controllers.api.user

import javax.inject.Inject

import play.api.mvc._
import play.api.libs.json._
import play.api.i18n.I18nSupport
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsUser
import json.writes.JsValueWritesUser
import lib.persistence.{UserRepository, UserPassRepository, AuthTokenRepository}
import play.api.libs.json.JsPath.json
import lib.model.User
import auth.TokenGenerator

class UserLoginController @Inject()(
  userRepo: UserRepository,
  passRepo: UserPassRepository,
  authRepo: AuthTokenRepository,
  cc:       MessagesControllerComponents
)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) with I18nSupport {

    def login() = Action async {implicit request =>
      val params = request.body.asJson
      val json   = params.get
      val result = json.validate[JsValueReadsUser]
      val user   = result.get
      for {
        mailUser <- userRepo.filterByMail(user.mail)
        passUser <- passRepo.filterByPass(user.password)
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
          case None    => authRepo.updateToken(userMailId, None)
          case Some(_) => authRepo.updateToken(userMailId, newToken)
        }
        println(newToken)
        val newCookie = Cookie("My-Xsrf-Cookie", newToken.getOrElse("No-Cookie"))
        println(newCookie)
        println(Ok.withCookies(newCookie))
        val jsWritesAuth = JsValueWritesUser.toWrites(
          mail     = user.mail,
          password = user.password
        )
        newToken match {
          case None    => BadRequest
          case Some(_) => Ok(Json.toJson(jsWritesAuth)).withCookies(newCookie)
        }
      }
    }
  }
