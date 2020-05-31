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

  def add(uid: User.Id, pass: String): Future[Int] =
    db.run {
      userPass += Pass(uid, pass)
    }

  def filterByPass(pass: String): Future[Option[Pass]] =
    db.run {
      userPass.filter(_.password === pass)
      .result.headOption
    }

  /******** 定義 ********/ 
  private class UserPassTable(tag: Tag) extends Table[Pass](tag, "userPassword"){
    def user_id       = column[User.Id]          ("user_id", O.PrimaryKey)
    def password      = column[String]           ("password")
    //def updatedAt     = column[LocalDateTime]    ("updatedAt")
    //def createdAt     = column[LocalDateTime]    ("createdAt")

    type TableElementTuple = (
      User.Id, String
    )

    def * = (user_id, password) <> (
      (x: TableElementTuple) => Pass(
        x._1, x._2
      ),
      (v: Pass) => Pass.unapply(v).map {t => (
        t._1, t._2
      )}
    )
  }
  private val userPass = TableQuery[UserPassTable]
}
