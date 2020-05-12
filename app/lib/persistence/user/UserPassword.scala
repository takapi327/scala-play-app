package lib.persistence

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

import lib.model.{User, UserPassword => Pass}

@Singleton
class UserPassRepository @Inject()
  (dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext){
    
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  /******** 定義 ********/ 
  private class UserPassTable(tag: Tag) extends Table[Pass](tag, "userPassword"){
    def id            = column[User.Id]          ("id", O.PrimaryKey)
    def password      = column[String]           ("password")
    //def updatedAt     = column[LocalDateTime]    ("updatedAt")
    //def createdAt     = column[LocalDateTime]    ("createdAt")

    type TableElementTuple = (
      Option[User.Id], String
    )

    def * = (id.?, password) <> (
      (x: TableElementTuple) => Pass(
        x._1, x._2
      ),
      (v: Pass) => Pass.unapply(v).map {t => (
        t._1, t._2
      )}
    )
  }
  private val user = TableQuery[UserPassTable]
}
