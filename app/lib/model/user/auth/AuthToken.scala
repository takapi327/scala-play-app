package lib.model

import java.time.LocalDateTime

case class AuthToken (
  id:     Option[String],
  userId: Option[User.Id]
)
