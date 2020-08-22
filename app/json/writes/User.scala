package json.writes

import play.api.libs.json._
import lib.model._

case class JsValueWritesUser(
  fullname: String,
)

object  JsValueWritesUser {
  implicit val userWrites = Json.writes[JsValueWritesUser]
}
