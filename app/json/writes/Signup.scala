package json.writes

import play.api.libs.json._
import lib.model._

case class JsValueWritesSignup(
  firstName: String,
  lastName:  String,
  email:     String,
  password:  String
)

object  JsValueWritesSignup {
  implicit val userWrites = Json.writes[JsValueWritesSignup]

  def toWrites(
    firstName: String,
    lastName:  String,
    email:     String,
    password:  String
  ): JsValueWritesSignup = {
    JsValueWritesSignup(
      firstName = firstName,
      lastName  = lastName,      
      email     = email,
      password  = password
    )
  }
}
