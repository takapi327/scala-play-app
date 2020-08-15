package controllers.api.user

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsSignup
import json.writes.{JsValueWritesSignup, JsValueWritesIsAuth}

import lib.persistence.{UserRepository, UserPassRepository, AuthTokenRepository}
import lib.model.{User, UserPassword => Pass}

import auth.TokenGenerator
import auth.AuthActionHelpers
import services.AuthActionService

import play.api.mvc._
import play.api.libs.json._
import play.api.i18n.I18nSupport
import play.api.libs.json.JsPath.json

class UserSignupController @Inject()(
  userRepo: UserRepository,
  passRepo: UserPassRepository,
  authRepo: AuthTokenRepository,
  authService: AuthActionService,
  cc:       MessagesControllerComponents
)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc)
  with    I18nSupport
  with    AuthActionHelpers {

    def signup() = Action(parse.json) async {implicit request =>
      val json   = request.body
      val result = json.validate[JsValueReadsSignup]
      val user   = result.get
      val withNoIdUser =
        User.buildEntity(
          user.firstName,
          user.lastName,
          user.email
        )
      val hashPass = Pass.hash(user.password)
      for {
        newUser <- userRepo.add(withNoIdUser)
        newPass <- passRepo.add(newUser, hashPass)
        token     = TokenGenerator().next(30)
        newCookie = Cookie("My-Xsrf-Cookie", token)
        result  <- authRepo.add(Some(token), Some(newUser))
      } yield {
        val jsWritesAuth =
          JsValueWritesSignup.toWrites(
            fullName = withNoIdUser.nameInfo.fullName,
            email    = withNoIdUser.email
          )
      Ok(Json.toJson(jsWritesAuth)).withCookies(newCookie)
      }
    }

    def list() = Action {implicit request =>
      val cookies = request.cookies.get("My-Xsrf-Cookie")
      val jsWritesAuth = JsValueWritesIsAuth.toWrites(
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
}
