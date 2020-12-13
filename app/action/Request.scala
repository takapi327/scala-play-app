package auth

import lib.model.User

import play.api.mvc.WrappedRequest
import play.api.mvc.Request
import play.api.i18n.MessagesApi

class UserRequest[A](
  val user:    Option[User],
  request:     Request[A], 
  messagesApi: MessagesApi
) extends WrappedRequest[A](request)
