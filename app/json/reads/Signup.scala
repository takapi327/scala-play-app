package json.reads

import play.api.libs.json._
import lib.model._

case class JsValueReadsSignup(
  firstName: String,
  lastName:  String,
  email:     String,
  password:  String
)

object  JsValueReadsSignup {
  implicit val signupReads = Json.reads[JsValueReadsSignup]
}
