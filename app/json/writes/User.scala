package json.writes


import play.api.libs.json._
import lib.model._

case class JsValueWritesUser(
  mail:     String,
  password: String
)

object  JsValueWritesUser {
  implicit val userWrites = Json.writes[JsValueWritesUser]

  def toWrites(mail: String, password: String): JsValueWritesUser = {
    JsValueWritesUser(
      mail     = mail,
      password = password
    )
  }
}
