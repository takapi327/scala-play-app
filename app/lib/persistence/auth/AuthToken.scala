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

  def add(uid: Option[User.Id], token: String): Future[Int] =
    db.run {
      userAuthToken += UserSession(uid, token)
    }

  /******** 定義 ********/ 
  private class AuthTokenTable(tag: Tag) extends Table[UserSession](tag, "userAuthToken"){
    def userId     = column[User.Id]          ("user_id")
    def token      = column[String]           ("token")
    //def updatedAt     = column[LocalDateTime]    ("updatedAt")
    //def createdAt     = column[LocalDateTime]    ("createdAt")

    type TableElementTuple = (
      Option[User.Id], String
    )

    def * = (userId.?, token) <> (
      (x: TableElementTuple) => UserSession(
        x._1, x._2
      ),
      (v: UserSession) => UserSession.unapply(v).map {t => (
        t._1, t._2
      )}
    )
  }
  private val userAuthToken = TableQuery[AuthTokenTable]
}
