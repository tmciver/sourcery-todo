package com.maxwellhealth.todoes.domain.model

import com.maxwellhealth.sourcery.{ Entity, Event }
import com.maxwellhealth.todoes.domain.events.TodoWasCreated
import java.time.Instant
import java.util.UUID

case class Todo(id: UUID, text: String, due: Instant) extends Entity[UUID] {

  override def applyEvent(event: Event[UUID]) = event match {
    case TodoWasCreated(_, _, newText, newDueDate) => Todo(id, newText, newDueDate)
    case _ => this
  }
}
