package com.maxwellhealth.todoes.view.models

import scala.collection.mutable.Map
import java.util.UUID

object TodoRepository {

  val todos: Map[UUID, Todo] = Map()

  def getAll(): Iterable[Todo] = todos.values

  def get(id: UUID): Option[Todo] = todos.get(id)

  def save(todo: Todo): Unit = todos(todo.id) = todo
}
