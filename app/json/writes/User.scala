package json.writes


import play.api.libs.json._
import lib.model._

case class JsValueWritesUser(
  email:    String,
  password: String
)

object  JsValueWritesUser {
  implicit val userWrites = Json.writes[JsValueWritesUser]

  def toWrites(email: String, password: String): JsValueWritesUser = {
    JsValueWritesUser(
      email    = email,
      password = password
    )
  }
}
