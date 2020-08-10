package model

import play.api.data.Form
import play.api.data.Forms._
import lib.model._

case class SignupForm(firstName: String, lastName: String, email: String, password: String)
case class LoginForm(email: String, password: String)

object StatusValue {
// formの作成
  val signupForm = Form(
    mapping(
      "firstName"    ->   nonEmptyText,
      "lastName"     ->   nonEmptyText,
      "email"        ->   nonEmptyText,
      "password"     ->   nonEmptyText
    )(SignupForm.apply)(SignupForm.unapply)
  )

  val loginForm = Form(
    mapping(
      "email"        ->   nonEmptyText,
      "password"     ->   nonEmptyText
    )(LoginForm.apply)(LoginForm.unapply)
  )
}
