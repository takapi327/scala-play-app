package auth

import model._
import lib.model._

import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc._
import play.api.i18n.MessagesApi
import play.mvc.Results._
import java.lang.ProcessBuilder.Redirect
import controllers.routes


trait AuthActionBuilder extends ActionBuilder[UserRequest, AnyContent]
object AuthActionBuilder {
  def apply(
    auth:        RequestHeader => Future[Option[User]],
    parser:      BodyParser[AnyContent],
    messagesApi: MessagesApi
  )(implicit ec: ExecutionContext) = {
    new AuthActionBuilderImpl(auth, parser, messagesApi)
  }
}

class AuthActionBuilderImpl(
  val auth:    RequestHeader => Future[Option[User]],
  val parser:  BodyParser[AnyContent],
  messagesApi: MessagesApi
)(implicit ec: ExecutionContext)
  extends AuthActionBuilder
  with    Results {

    override def executionContext: ExecutionContext = ec

    def invokeBlock[A](
      request: Request[A],
      block:   UserRequest[A] => Future[Result]
    ): Future[Result] = auth(request) flatMap { optUser =>
      optUser match {
        case Some(user) => block(new UserRequest(Some(user), request, messagesApi))
        case None       => block(new UserRequest(None, request, messagesApi))
      }
    }
  }


