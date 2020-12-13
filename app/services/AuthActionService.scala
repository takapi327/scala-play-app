package services

import model._
import lib.model._
import lib.persistence._
import auth._

import javax.inject._
import scala.concurrent.{ ExecutionContext, Future }

import play.api.mvc._

@Singleton
class AuthActionService @Inject()(
  userRepo: UserRepository,
  passRepo: UserPassRepository,
  authRepo: AuthTokenRepository,
) extends AuthHelpers {

  def authenticate(request: RequestHeader)(implicit ec: ExecutionContext): Future[Option[User]] = {
    request.cookies.get(COOKIES_NAME) match {
      case None           => Future.successful(None)
      case Some(username) => {
        for {
          authUser <- authRepo.filterByToken(username.value)
          optUser  <- authUser match {
            case Some(user) => userRepo.filterById(user.userId.getOrElse(0))
            case None       => Future(None)
          }
        } yield {
          optUser
        }
      }
    }
  }
}

