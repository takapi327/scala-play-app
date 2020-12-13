package controllers.api.authenticate

import javax.inject.Inject
import scala.concurrent.{Future, ExecutionContext}

import json.reads.JsValueReadsUser
import json.writes.JsValueWritesUser

import cats.data.EitherT
import cats.implicits._

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

  def login() = Action(parse.json) async {implicit request =>
    val jsonUser = request.body.validate[JsValueReadsUser].get
    val result: EitherT[Future, Result, Result] =
      for {
        user     <- EitherT(userRepo.filterByMail(jsonUser.email).map(_.toRight(NotFound("Not Foumd Email"))))
        passUser <- EitherT(passRepo.filterById(user.id.get).map(_.toRight(NotFound("Not Found Id"))))
      } yield {
        val newToken    = TokenGenerator().next(30)
        val updateToken = UserPassword.verify(jsonUser.password, passUser.hash) match {
          case true  => Some(authRepo.updateToken(Some(newToken), user.id))
          case false => None
        }
        updateToken match {
          case None    => BadRequest
          case Some(_) => Ok(
            Json.toJson(JsValueWritesUser(user.nameInfo.fullName))
          ).withCookies(Cookie("My-Xsrf-Cookie", newToken))
        }
      }
    result.value.map{
      case Right(sucess) => sucess
      case Left(bad)     => bad
    }
  }
}
