package lib.model

import java.time.LocalDateTime

import User._

case class UserId (id: Long)

case class UserName (
  firstName: String,
  lastName:  String
) {
  lazy val fullName =
	s"${firstName} ${lastName}"
}

case class User (
  id:       Option[User.Id],
  nameInfo: UserName,
  mail:     String
)

object User {

  type Id   = Long
  type name = String
  type mail = String
  
  def apply(firstName: String, lastName: String, mail: String): User = {
    new User(
      id       = None,
      nameInfo = UserName(firstName, lastName),
      mail     = mail
    )
  }
  
}
