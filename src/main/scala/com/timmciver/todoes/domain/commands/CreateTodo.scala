package com.maxwellhealth.todoes.domain.commands

import com.maxwellhealth.sourcery.Command
import com.maxwellhealth.todoes.domain.events.TodoWasCreated
import java.time.Instant
import java.util.UUID

case class CreateTodo(text: String, due: Instant) extends Command[UUID, String] {
  override def id = UUID.randomUUID

  def handle = Right(List(TodoWasCreated(id, text, due)))
}
