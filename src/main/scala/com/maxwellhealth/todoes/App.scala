package com.maxwellhealth.todoes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn

import com.maxwellhealth.todoes.view.models.{ Todo, TodoRepository }
import com.maxwellhealth.todoes.view.Html.{ home, todosHtml, xmlToString, createFormHtml }

import java.util.UUID
import java.time.Instant

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
          TodoRepository.save(Todo(UUID.randomUUID, desc, Instant.now, Instant.now, Instant.now))
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
