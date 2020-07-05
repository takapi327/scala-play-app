package model

import lib.model.User
import play.api.data.Form
import play.api.data.Forms._

case class ViewValueUserList(
  title:  String       = "マイページ",
  cssSrc: Seq[String]  = Seq("main.css"),
  jsSrc:  Seq[String]  = Seq("main.js"),
  user:   Option[User]
) extends ViewValueCommon

case class ViewValueUserAdd(
  title:      String                 = "Sign up",
  cssSrc:     Seq[String]            = Seq("main.css"),
  jsSrc:      Seq[String]            = Seq("main.js"),
  form:       Form[model.SignupForm] = StatusValue.signupForm
) extends ViewValueCommon

case class ViewValueUserLogin(
  title:      String                = "Login",
  cssSrc:     Seq[String]           = Seq("main.css"),
  jsSrc:      Seq[String]           = Seq("main.js"),
  form:       Form[model.LoginForm] = StatusValue.loginForm
) extends ViewValueCommon
