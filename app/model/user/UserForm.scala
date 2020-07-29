package model

import play.api.data.Form
import play.api.data.Forms._
import lib.model._

case class SignupForm(name: String, mail: String, password: String)
case class LoginForm(mail: String, password: String)

object StatusValue {
// formの作成
  val signupForm = Form(
    mapping(
      "name"         ->   nonEmptyText,
      "mail"         ->   nonEmptyText,
      "password"     ->   nonEmptyText
    )(SignupForm.apply)(SignupForm.unapply)
  )

  val loginForm = Form(
    mapping(
      "mail"         ->   nonEmptyText,
      "password"     ->   nonEmptyText
    )(LoginForm.apply)(LoginForm.unapply)
  )
}
