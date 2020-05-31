package lib.model

import java.time.LocalDateTime
import java.security.Identity

import UserPassword._

case class UserPassword (
  user_id:     User.Id,
  password:    String,
  //updateAt:    LocalDateTime,
  //createdAt:   LocalDateTime
)

object UserPassword {

  //case class FormValue (password: String)
}
