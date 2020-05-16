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

  def add(name: String, mail: String): Future[Int] =
    db.run {
      user += User(Some(0), name, mail)
    }

  def filterByMail(umail: String): Future[Seq[User]] =
    db.run {
      user.filter(_.mail === umail)
      .result
    }


  /******** 定義 ********/ 
  private class UserTable(tag: Tag) extends Table[User](tag, "user"){
    def id            = column[User.Id]          ("id", O.PrimaryKey, O.AutoInc)
    def name          = column[String]           ("name")
    def mail          = column[String]           ("mail")
    //def updatedAt     = column[LocalDateTime]    ("updated_at")
    //def createdAt     = column[LocalDateTime]    ("created_at")
/*
    def * = (id, passId, name, mail, updatedAt, createdAt) <> (
      (User.apply _).tupled, User.unapply
    )
*/
    type TableElementTuple = (
      Option[User.Id], String, String
    )

    def * = (id.?, name, mail) <> (
      (x: TableElementTuple) => User(
        x._1, x._2 ,x._3
      ),
      (v: User) => User.unapply(v).map {t => (
        //Some((user.id, user.passId, user.name, user.mail, user.updateAt, user.createdAt))
        t._1, t._2 , t._3
      )}
    )
  }
  private val user = TableQuery[UserTable]
}
