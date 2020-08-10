/*
package lib.persistence

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import javax.inject.{Inject, Singleton}
import lib.model.User

case class Db @Inject()
  (dbConfigProvider: DatabaseConfigProvider) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
*/
/******** 定義 ********/
/*
private class UserTable(tag: Tag) extends Table[User](tag, "user"){
  def id            = column[User.Id]          ("id", O.PrimaryKey, O.AutoInc)
  def name          = column[String]           ("name")
  def mail          = column[String]           ("mail")
  //def updatedAt     = column[LocalDateTime]    ("updated_at")
  //def createdAt     = column[LocalDateTime]    ("created_at")

  type TableElementTuple = (
    Option[User.Id], String, String
  )

  def * = (id.?, name, mail) <> (
    (x: TableElementTuple) => User(
      x._1, x._2 ,x._3
    ),
    (v: User) => User.unapply(v).map {t => (
      t._1, t._2 , t._3
    )}
  )
}
private val user = TableQuery[UserTable]
}

object db extends Db
*/
