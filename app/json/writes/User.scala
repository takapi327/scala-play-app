package json.writes

import play.api.libs.json._
import lib.model._

case class JsValueWritesUser(
  fullname: String,
)

object JsValueWritesUser {
  implicit val userWrites = Json.writes[JsValueWritesUser]
}

case class JsValueWritesEmailValid(
  isRegistered: Boolean,
)

object JsValueWritesEmailValid {
  implicit val emaliValidWrites = Json.writes[JsValueWritesEmailValid]
}
