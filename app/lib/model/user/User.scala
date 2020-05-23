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

  type Id   = Long
  type name = String
  type mail = String
  
  def apply(id: Option[Id], name: String, mail: String): User = {
    new User(
      id   = id,
      name = name,
      mail = mail
    )
  }
  
  //case class FormValue (name: String, mail: String)
}
