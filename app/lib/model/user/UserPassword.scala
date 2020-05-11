package lib.model

import java.time.LocalDateTime
import java.security.Identity

import UserPassword._

case class UserPassword (
  id:          Option[Id],
  password:    String,
  repassword:  String,
  //updateAt:    LocalDateTime,
  //createdAt:   LocalDateTime
)

object UserPassword {

  type Id = Long

  case class FormValue (password: String, repassword: String)
}
