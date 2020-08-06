package json.reads

import play.api.libs.json._
import lib.model._

case class JsValueReadsUser(
  email:    String,
  password: String
)

object  JsValueReadsUser {
  implicit val userReads = Json.reads[JsValueReadsUser]
}
