package model

import play.api.data.Form
import play.api.data.Forms._
import lib.model._

case class SignupForm(name: String, email: String, password: String)

object StatusValue {
// formの作成
/*
  val userForm: Form[User.FormValue] = Form (
    mapping(
      "name"   ->   nonEmptyText,
      "mail"   ->   nonEmptyText
    )(User.FormValue.apply)(User.FormValue.unapply)
  )

  val passForm: Form[UserPassword.FormValue] = Form (
    mapping(
      "password"     ->   nonEmptyText
    )(UserPassword.FormValue.apply)(UserPassword.FormValue.unapply)
  )
*/
  val signupForm = Form(
    mapping(
      "name"         ->   nonEmptyText,
      "mail"         ->   nonEmptyText,
      "password"     ->   nonEmptyText
    )(SignupForm.apply)(SignupForm.unapply)
  )

}
