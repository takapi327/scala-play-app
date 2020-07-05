package action.auth

import model._

import javax.inject._
import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc._
import play.api.i18n.MessagesApi
import play.mvc.Results._
import java.lang.ProcessBuilder.Redirect
import controllers.routes

@Singleton
class IsAlreadyLoginAction @Inject()(
  playBodyParsers: PlayBodyParsers,
  messagesApi:     MessagesApi
)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[MessagesRequest, AnyContent] {

  override def parser: BodyParser[AnyContent] = playBodyParsers.anyContent

  override def invokeBlock[A](
    request: Request[A], 
    block:   MessagesRequest[A] => Future[Result]
  ): Future[Result] = {
    request.cookies.get("user") match {
      case Some(_) => Future(Results.BadRequest(views.html.error.page404(new ViewValueError)))
      case None    => block(new MessagesRequest(request, messagesApi))
    }
  }
}
