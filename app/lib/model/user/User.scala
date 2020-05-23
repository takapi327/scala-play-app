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
  type name = String
  type mail = String
 /* 
  def apply(name: String, mail: String): User = {
    new User(
      name = name,
      mail = mail
    )
  }
  */
  //case class FormValue (name: String, mail: String)
}
