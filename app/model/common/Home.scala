package model

case class ViewValueHome(
  title:  String,
  cssSrc: Seq[String],
  jsSrc:  Seq[String]
) extends ViewValueCommon
