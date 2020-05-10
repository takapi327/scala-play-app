/*
package lib.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import lib.model.UserPassword

case class UserPassTable()
  (dbConfigProvider: DatabaseConfigProvider){

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import lib.model.{UserPassword => Pass}

  private class UserPassTable(tag: Tag) extends Table[UserPassword](tag, "userPassword"){
    def id            = column[Option[Pass.Id]]  ("id", O.PrimaryKey, O.AutoInc)
    def password      = column[String]           ("password")
    def repassword    = column[String]           ("repassword")
    def updatedAt     = column[LocalDateTime]    ("updated_at")
    def createdAt     = column[LocalDateTime]    ("created_at")

    def * = (id, password, repassword, updatedAt, createdAt) <> (
      (UserPassword.apply _).tupled, UserPassword.unapply
    )
  }
}
*/
