package com.maxwellhealth.todoes.domain.commands

import com.maxwellhealth.sourcery.Command
import com.maxwellhealth.todoes.domain.events.TodoWasCreated
import java.time.Instant
import java.util.UUID

case class CreateTodo(text: String, due: Instant) extends Command[UUID, String] {
  def id = UUID.randomUUID
  val entityId = UUID.randomUUID

  def handle = Right(List(TodoWasCreated(id, entityId, text, due)))
}
