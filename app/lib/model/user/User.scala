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
  email:    String
)

object User {

  type Id   = Long
  type name = String
  type mail = String
  
  def buildEntity(firstName: String, lastName: String, email: String): User = {
    new User(
      id       = None,
      nameInfo = UserName(firstName, lastName),
      email    = email
    )
  }
  
}
