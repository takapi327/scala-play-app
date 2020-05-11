package model

import play.api.data.Form
import play.api.data.Forms._

case class ViewValueUserList(
  title:  String,
  cssSrc: Seq[String],
  jsSrc:  Seq[String],
  data:   Seq[lib.model.User]
) extends ViewValueCommon
