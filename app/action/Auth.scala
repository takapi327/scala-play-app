package auth

import model._
import lib.model._
import lib.persistence._

import javax.inject._
import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc._
import play.api.i18n.MessagesApi
import play.mvc.Results._
import play.api.libs.typedmap.TypedKey
import java.lang.ProcessBuilder.Redirect
import controllers.routes

trait AuthHelpers {
  val COOKIES_NAME = "My-Xsrf-Cookie"
}

trait AuthActionHelpers {
  self: BaseControllerHelpers =>

  def AuthAction(authenticate: RequestHeader => Future[Option[User]])(implicit ec: ExecutionContext) = {
    AuthActionBuilder(authenticate, parse.default, messagesApi)
  }
}

case class Attr(user: User)
object Attr {
  val KEY = TypedKey[Attr]
}
