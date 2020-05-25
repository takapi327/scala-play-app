package model

case class ViewValueError(
  title:  String      = "該当のページは存在しません。",
  cssSrc: Seq[String] = Seq("main.css"),
  jsSrc:  Seq[String] = Seq("main.js")
) extends ViewValueCommon
