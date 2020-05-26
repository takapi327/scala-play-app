package lib.model

import java.time.LocalDateTime

case class UserSession (
  userId:      Option[User.Id],
  token:       String
  //updateAt:    LocalDateTime,
  //createdAt:   LocalDateTime
)
