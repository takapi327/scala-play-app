package lib.persistence

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

import lib.model.{UserPassword => Pass}

@Singleton
class UserPassRepository @Inject()
  (dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext){
    
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  /******** 定義 ********/ 
  private class UserPassTable(tag: Tag) extends Table[Pass](tag, "userPassword"){
    def id            = column[Pass.Id]          ("id", O.PrimaryKey, O.AutoInc)
    def password      = column[String]           ("password")
    def repassword    = column[String]           ("repassword")
    //def updatedAt     = column[LocalDateTime]    ("updatedAt")
    //def createdAt     = column[LocalDateTime]    ("createdAt")

    type TableElementTuple = (
      Pass.Id, String, String
    )

    def * = (id, password, repassword) <> (
      (x: TableElementTuple) => Pass(
        Some(x._1), x._2 ,x._3
      ),
      (v: Pass) => Pass.unapply(v).map {t => (
        t._1.getOrElse(0L), t._2 , t._3
      )}
    )
  }
  private val user = TableQuery[UserPassTable]
}
