package model

import play.api.data.Form
import play.api.data.Forms._

case class ViewValueUserList(
  title:  String ,
  cssSrc: Seq[String],
  jsSrc:  Seq[String]
 // data:   Seq[lib.model.User]
  //cookie: String, //Option[play.api.mvc.Cookie],
  //either: Either[String, lib.model.User]
) extends ViewValueCommon

case class ViewValueUserAdd(
  title:      String                      = "Sign up",
  cssSrc:     Seq[String]                 = Seq("main.css"),
  jsSrc:      Seq[String]                 = Seq("main.js"),
  form:       Form[model.SignupForm]  = StatusValue.signupForm
  //passForm:   Form[lib.model.UserPassword.FormValue] = StatusValue.passForm
) extends ViewValueCommon
