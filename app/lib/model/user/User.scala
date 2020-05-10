package lib.model

import java.time.LocalDateTime
import lib.model.{UserPassword => Pass}

import User._

case class User (
  id:          Option[Id],
  passId:      Option[Pass.Id],
  name:        String,
  mail:        String,
  updateAt:    LocalDateTime,
  createdAt:   LocalDateTime
)

object User {

  type Id = Long
  
  case class FormValue (name: String, mail: String)
}
