package controllers.api.authenticate

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsUser
import json.writes.JsValueWritesUser

import lib.persistence.{UserRepository, UserPassRepository, AuthTokenRepository}
import lib.model.{User, UserPassword}

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

    /**
     *
     * ※要修正
     * EitherT等を使ってもっと短く書きたい
     */
    def login() = Action(parse.json) async {implicit request =>
      val json     = request.body
      val result   = json.validate[JsValueReadsUser]
      val jsonUser = result.get
      for {
        user     <- userRepo.filterByMail(jsonUser.email)
        /**
         * ※要修正
         * IdにgetOrElseはNG
         */
        passUser <- passRepo.filterById(user.flatMap(v => v.id).getOrElse(0))
      } yield {
        val isMatchPass = passUser match {
          case Some(pass) => UserPassword.verify(jsonUser.password, pass.hash)
          case None       => false
        }
        val newToken   =
          isMatchPass match {
            case true  => Some(TokenGenerator().next(30))
            case false => None
          }

        newToken match {
          case None    => authRepo.updateToken(None, None)
          case Some(_) => authRepo.updateToken(newToken, user.flatMap(v => v.id))
        }
        val newCookie    = Cookie("My-Xsrf-Cookie", newToken.getOrElse("No-Cookie"), domain = Some("localhost"))
        val jsWritesAuth = JsValueWritesUser(
          fullname = user match {
            case Some(v) => v.nameInfo.fullName
            case None    => "Not Found User Name"
          }
        )
        newToken match {
          case None    => BadRequest
          case Some(_) => Ok(Json.toJson(jsWritesAuth)).withCookies(newCookie)
        }
      }
    }
  }
