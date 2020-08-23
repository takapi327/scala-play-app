package controllers.api.authenticate

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsSignup
import json.writes.{JsValueWritesSignup, JsValueWritesIsAuth}

import cats.data.EitherT
import cats.implicits._

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
  userRepo:    UserRepository,
  passRepo:    UserPassRepository,
  authRepo:    AuthTokenRepository,
  authService: AuthActionService,
  cc:          MessagesControllerComponents
)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc)
  with    I18nSupport
  with    AuthActionHelpers {

  def signup() = Action(parse.json) async {implicit request =>
    val jsonUser = request.body.validate[JsValueReadsSignup].get
    val withNoIdUser =
      User.buildEntity(
        jsonUser.firstName,
        jsonUser.lastName,
        jsonUser.email
      )
    val hashPass = Pass.hash(jsonUser.password)
    val result: EitherT[Future, Result, Result] =
      for {
        newUser <- EitherT(userRepo.add(withNoIdUser).map(Either.right(_)))
        newPass <- EitherT(passRepo.add(newUser, hashPass).map(Either.right((_))))
        newToken = TokenGenerator().next(30)
        _       <- EitherT(authRepo.add(Some(newToken), Some(newUser)).map(Either.right(_)))
      } yield {
        Ok(Json.toJson(
          JsValueWritesSignup(
            withNoIdUser.nameInfo.fullName,
            withNoIdUser.email
          )
        )).withCookies(Cookie("My-Xsrf-Cookie", newToken))
      }
      result.value.map{
        case Left(bad)      => bad
        case Right(success) => success
      }
  }

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
}
