package lib.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import lib.model._
import lib.service.SlickDatabaseConfig
import lib.service.SlickDatabaseConfig.profile._
import slick.lifted.Tag

class UserPassTable(tag: Tag) extends Table[UserPassword](tag, "userPassword") with SlickDatabaseConfig {

  import profile.api._

  def user_id       = column[User.Id]          ("user_id", O.PrimaryKey)
  def password      = column[String]           ("password")
  //def updatedAt     = column[LocalDateTime]    ("updatedAt")
  //def createdAt     = column[LocalDateTime]    ("createdAt")

  type TableElementTuple = (
    User.Id, String
  )

  def * = (user_id, password) <> (
    (x: TableElementTuple) => UserPassword(
      x._1, x._2
    ),
  (v: UserPassword) => UserPassword.unapply(v).map {t => (
      t._1, t._2
    )}
  )
}
