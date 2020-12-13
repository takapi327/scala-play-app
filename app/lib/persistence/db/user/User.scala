package lib.persistence

import lib.service.SlickDatabaseConfig
import lib.service.SlickDatabaseConfig.profile._
import java.time.LocalDateTime
import lib.model._
import slick.lifted.Tag

/******** 定義 ********/
class UserTable(tag: Tag) extends Table[User](tag, "user") with SlickDatabaseConfig {

  import profile.api._

  def id            = column[User.Id]          ("id", O.PrimaryKey, O.AutoInc)
  def firstName     = column[String]           ("first_name")
  def lastName      = column[String]           ("last_name")
  def email         = column[String]           ("email")
  //def updatedAt     = column[LocalDateTime]    ("updated_at")
  //def createdAt     = column[LocalDateTime]    ("created_at")

  type TableElementTuple = (
    Option[User.Id], String, String, String
  )

  def * = (id.?, firstName, lastName, email) <> (
    (x: TableElementTuple) => User(
      x._1, UserName(x._2, x._3), x._4
    ),
    (v: User) => User.unapply(v).map {t => (
      t._1, t._2.firstName, t._2.lastName, t._3
    )}
  )
}

class AuthTokenTable(tag: Tag) extends Table[AuthToken](tag, "userAuthToken") with SlickDatabaseConfig {

  import profile.api._

  def id      = column[String]   ("id")
  def userId  = column[User.Id]  ("user_id", O.PrimaryKey)
  //def updatedAt     = column[LocalDateTime]    ("updatedAt")
  //def createdAt     = column[LocalDateTime]    ("createdAt")

  type TableElementTuple = (
    Option[String], Option[User.Id]
  )

  def * = (id.?, userId.?) <> (
    (x: TableElementTuple) => AuthToken(
      x._1, x._2
    ),
    (v: AuthToken) => AuthToken.unapply(v).map {t => (
      t._1, t._2
    )}
  )
}
