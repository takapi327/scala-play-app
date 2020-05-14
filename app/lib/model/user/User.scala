package lib.model

import java.time.LocalDateTime

import User._

case class User (
  id:          Option[Id],
  name:        String,
  mail:        String
  //updateAt:    LocalDateTime,
  //createdAt:   LocalDateTime
)

object User {

  type Id = Long
  
  //case class FormValue (name: String, mail: String)
}
