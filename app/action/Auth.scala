package action.auth

import model._

import javax.inject._
import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc._
import play.api.i18n.MessagesApi
import play.mvc.Results._


class UserRequest[A](val user: String, request: Request[A], messagesApi: MessagesApi) extends WrappedRequest[A](request)

@Singleton
class AuthAction @Inject()(
  playBodyParsers: PlayBodyParsers,
  messagesApi:     MessagesApi
)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] {

  override def parser: BodyParser[AnyContent] = playBodyParsers.anyContent

  override def invokeBlock[A](
    request: Request[A], 
    block:   UserRequest[A] => Future[Result]
  ): Future[Result] = {
    request.cookies.get("user") match {
      case Some(username) => block(new UserRequest(username.value, request, messagesApi))
      case None           => Future(Results.Status(404))
    }
  }
}

