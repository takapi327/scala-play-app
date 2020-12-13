package json.writes

import play.api.libs.json._
import lib.model._

case class JsValueWritesSignup(
  fullName: String,
  email:    String
)

object  JsValueWritesSignup {
  implicit val userWrites = Json.writes[JsValueWritesSignup]
}
