package com.maxwellhealth.todoes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn

import com.maxwellhealth.todoes.view.models.{ Todo, TodoRepository }
import com.maxwellhealth.todoes.view.Html.{ home, todosHtml, xmlToString, createFormHtml }
import com.maxwellhealth.todoes.domain.commands.CreateTodo
import com.maxwellhealth.todoes.domain.events.eventListeners

import java.util.UUID
import java.time.Instant
import java.time.temporal.ChronoUnit

object App {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route =
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, xmlToString(home)))
      } ~
    path("todos") {
      get {
        val todos = TodoRepository.getAll
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, xmlToString(todosHtml(todos))))
      } ~
      post {
        formFields('desc) { (desc: String) =>
          val due = Instant.now.plus(1, ChronoUnit.DAYS)
          val command = CreateTodo(desc, due)
          command.dispatch(eventListeners)
          redirect("/todos", StatusCodes.SeeOther)
        }
      }
    } ~
    path("todo-form") {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, xmlToString(createFormHtml)))
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8081)

    println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
