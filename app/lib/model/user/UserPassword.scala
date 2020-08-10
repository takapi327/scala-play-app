package lib.model

import java.time.LocalDateTime
import java.security.Identity
import io.github.nremond.SecureHash

import UserPassword._

case class UserPassword (
  userId:  User.Id,
  hash:    String
  //updateAt:    LocalDateTime,
  //createdAt:   LocalDateTime
)

object UserPassword {

  def hash(password: String): String =
    SecureHash.createHash(password)

  def verify(password: String, hash: String): Boolean =
    SecureHash.validatePassword(password, hash)
}
