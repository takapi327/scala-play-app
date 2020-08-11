package lib.persistence

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

import lib.model.{UserId, UserName, User, UserPassword => Pass}

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

  def add(newUser: User): Future[Long] =
    db.run {
      (user returning user.map(_.id)) += newUser
    }

  def filterByMail(umail: String): Future[Option[User]] =
    db.run {
      user.filter(_.email === umail)
      .result.headOption
    }

  def filterById(uid: Long): Future[Option[User]] =
    db.run {
      user.filter(_.id === uid)
      .result.headOption
    }

  /******** 定義 ********/
  private class UserTable(tag: Tag) extends Table[User](tag, "user"){
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
  private val user = TableQuery[UserTable]
}
