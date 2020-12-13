package model

case class ViewValueHome(
  title:  String       = "Home",
  cssSrc: Seq[String]  = Seq("main.css"),
  jsSrc:  Seq[String]  = Seq("main.js")
) extends ViewValueCommon
