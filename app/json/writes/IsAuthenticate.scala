package json.writes

import play.api.libs.json._
import lib.model._

case class JsValueWritesIsAuth(
  isAuth: Boolean
)

object  JsValueWritesIsAuth {
  implicit val isAuthWrites = Json.writes[JsValueWritesIsAuth]
}

