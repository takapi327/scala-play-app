package lib.persistence

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

import lib.model.{User, UserPassword => Pass}

@Singleton
class UserRepository @Inject()
  (dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext){
    
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def getAll(): Future[Seq[User]] =
    db.run {
      user.result
    }

  /******** 定義 ********/ 
  private class UserTable(tag: Tag) extends Table[User](tag, "user"){
    def id            = column[User.Id]          ("id", O.PrimaryKey, O.AutoInc)
    def passId        = column[Option[Pass.Id]]  ("passId")
    def name          = column[String]           ("name")
    def mail          = column[String]           ("mail")
    def updatedAt     = column[LocalDateTime]    ("updatedAt")
    def createdAt     = column[LocalDateTime]    ("createdAt")
/*
    def * = (id, passId, name, mail, updatedAt, createdAt) <> (
      (User.apply _).tupled, User.unapply
    )
*/
    type TableElementTuple = (
      User.Id, Option[Pass.Id], String, String, LocalDateTime, LocalDateTime
    )

    def * = (id, passId, name, mail, updatedAt, createdAt) <> (
      (x: TableElementTuple) => User(
        Some(x._1), x._2 ,x._3, x._4, x._5, x._6
      ),
      (v: User) => User.unapply(v).map {t => (
        //Some((user.id, user.passId, user.name, user.mail, user.updateAt, user.createdAt))
        t._1.getOrElse(0L), t._2 , t._3, t._4, t._5, t._6
      )}
    )
  }
  private val user = TableQuery[UserTable]
}
