package com.maxwellhealth.todoes.view

import com.maxwellhealth.todoes.view.models.Todo

// An XML AST
sealed trait XmlElement
sealed trait BodyElement extends XmlElement
sealed trait TableElement extends BodyElement
case class Xhtml(title: String, body: Body) extends XmlElement
case class Body(el: BodyElement) extends XmlElement
case class BodyElementSeq(els: Iterable[BodyElement]) extends BodyElement
case class Heading(text: String) extends BodyElement
case class Link(text: String, url: String) extends BodyElement
case class Table(header: TableHeader, rows: Iterable[TableRow]) extends BodyElement
case class TableHeader(columnHeaders: Iterable[String]) extends TableElement
case class TableRow(row: Iterable[String]) extends TableElement

object Html {

  def home = {
    val heading = Heading("Sourcery Todo")
    val todosLink = Link("View your todos", "/todos")
    val body = Body(BodyElementSeq(List(heading, todosLink)))

    Xhtml("Sourcery Todos", body)
  }

  def todosHtml(todos: Iterable[Todo]): Xhtml = {
    val heading = Heading("Todos")
    val tableHeader = TableHeader(List("ID", "Description"))
    val tableRows = todos.map(todo => TableRow(List(todo.id.toString, todo.text)))
    val table = Table(tableHeader, tableRows)
    val body = Body(BodyElementSeq(List(heading, table)))

    Xhtml("Todos", body)
  }

  def xmlToString(xml: XmlElement): String = xml match {
    case Xhtml(title, body) => {
      val bodyString = xmlToString(body)
      raw"""
<html>
  <head>
    <title>$title</title>
  </head>
  <body>
    $bodyString
  </body>
</html>
"""
    }
    case Body(body) => xmlToString(body)
    case BodyElementSeq(els) => els.map(xmlToString).mkString("\n")
    case Heading(h) => s"<h3>$h</h3>"
    case Link(text, url) => raw"""<a href="$url">$text</a>"""
    case Table(header, rows) => {
      val headerString = xmlToString(header)
      val rowsString = rows.map(row => xmlToString(row)).mkString("\n")
      raw"""
<table border=1>
  $headerString
  $rowsString
</table>
"""
    }
    case TableHeader(headers) => {
      val headerString = headers.map(header => s"<th>$header</th>").mkString("")
      s"<tr>$headerString</tr>"
    }
    case TableRow(rowVals) => {
      val rowString = rowVals.map(rowVal => s"<td>$rowVal</td>").mkString("")
      s"<tr>$rowString</tr>"
    }
  }

  // def todosHtml(todos: Iterable[Todo]): String = {
//     val tableHeading = "<h3>Todos</h3>"
//     val 
//     val todoTable = raw"""

// """
//     wrapBodyHtml("testTitle", "Hello World!")
//   }

  def wrapBodyHtml(title: String, body: String): String =
    raw"""
<html>
  <head>
    <title>$title</title>
  </head>
  <body>
    $body
  </body>
</html>
"""

}
