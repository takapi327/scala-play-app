package json.reads

import play.api.libs.json._
import lib.model._

case class JsValueReadsUser(
  mail:     String,
  password: String
)

object  JsValueReadsUser {
  implicit val userReads = Json.reads[JsValueReadsUser]
/*
  def login(user: JsValueReadsUser): User = {
  
    User(
      id    = user.id,
      name  = user.name,
      mail  = user.mail
    )
  }
  */
}
