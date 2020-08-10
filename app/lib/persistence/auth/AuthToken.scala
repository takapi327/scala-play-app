package lib.persistence

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

import lib.model._

@Singleton
class AuthTokenRepository @Inject()
  (dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext){
    
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def add(id: Option[String], uid: Option[User.Id]): Future[Int] =
    db.run {
      userAuthToken += AuthToken(id, uid)
    }

  def filterByToken(id: String): Future[Option[AuthToken]] =
    db.run {
      userAuthToken.filter(_.id === id)
      .result.headOption
    }

  def updateToken(id: Option[String], uid: Option[User.Id]): Future[Int] =
    db.run {
      userAuthToken.insertOrUpdate(AuthToken(id, uid))
    }

  def deleteToken(id: String): Future[Int] =
    db.run {
      userAuthToken.filter(_.id === id).delete
    }

  /******** 定義 ********/ 
  private class AuthTokenTable(tag: Tag) extends Table[AuthToken](tag, "userAuthToken"){
    def id      = column[String]   ("id")
    def userId  = column[User.Id]  ("user_id", O.PrimaryKey)
    //def updatedAt     = column[LocalDateTime]    ("updatedAt")
    //def createdAt     = column[LocalDateTime]    ("createdAt")

    type TableElementTuple = (
      Option[String], Option[User.Id]
    )

    def * = (id.?, userId.?) <> (
      (x: TableElementTuple) => AuthToken(
        x._1, x._2
      ),
      (v: AuthToken) => AuthToken.unapply(v).map {t => (
        t._1, t._2
      )}
    )
  }
  private val userAuthToken = TableQuery[AuthTokenTable]
}
