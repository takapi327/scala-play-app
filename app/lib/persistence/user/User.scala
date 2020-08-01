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

  def add(name: String, mail: String): Future[Long] =
    db.run {
      (user returning user.map(_.id)) += User(Some(0), name, mail)
    }

  def filterByMail(umail: String): Future[Option[User]] =
    db.run {
      user.filter(_.mail === umail)
      .result.headOption
    }

  def filterById(uid:Long): Future[Option[User]] =
    db.run {
      user.filter(_.id === uid)
      .result.headOption
    }

  def signup(uid: Option[User.Id], name: User.name, mail: User.mail): Either[String, User] = {
    uid match {
      case Some(_) => Right(User(uid, name, mail))
      case None    => Left("Not Found")
    }
  }

  /******** 定義 ********/ 
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
