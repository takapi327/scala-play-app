package action.auth

import model._
import lib.model._
import lib.persistence._

import javax.inject._
import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc._
import play.api.i18n.MessagesApi
import play.mvc.Results._
import java.lang.ProcessBuilder.Redirect
import controllers.routes

@Singleton
class AuthAction @Inject()(
  userRepo:        UserRepository,
  passRepo:        UserPassRepository,
  authRepo:        AuthTokenRepository,
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
      case None           => Future(Results.BadRequest(views.html.error.page404(new ViewValueError)))
      case Some(username) => {
        for {
          authUser <- authRepo.filterByToken(username.value) 
          user <- authUser match { 
            case Some(user) => userRepo.filterById(user.userId.getOrElse(0))
            case None       => Future(None)
          }
          result   <- block(new UserRequest(user, request, messagesApi)) 
        } yield {
          result
        }
      }
    }
  }
}

