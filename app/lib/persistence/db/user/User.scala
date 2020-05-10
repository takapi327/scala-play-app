/*
package lib.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider

case class UserTable()
  (dbConfigProvider: DatabaseConfigProvider){

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import lib.model.{User, UserPassword => Pass}

  private class UserTable(tag: Tag) extends Table[User](tag, "user"){
    def id            = column[Option[User.Id]]  ("id", O.PrimaryKey, O.AutoInc)
    def passId        = column[Option[Pass.Id]]  ("passId")
    def name          = column[String]           ("name")
    def mail          = column[String]           ("mail")
    def updatedAt     = column[LocalDateTime]    ("updated_at")
    def createdAt     = column[LocalDateTime]    ("created_at")

    def * = (id, passId, name, mail, updatedAt, createdAt) <> (
      (User.apply _).tupled, User.unapply
    )
  }

  //private val user = TableQuery[UserTable]
}
*/
