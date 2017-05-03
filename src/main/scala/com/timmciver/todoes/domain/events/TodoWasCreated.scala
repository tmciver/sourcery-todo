package com.maxwellhealth.todoes.domain.events

import com.maxwellhealth.sourcery.Event
import java.time.Instant
import java.util.UUID

case class TodoWasCreated(aCommandId: UUID, text: String, due: Instant) extends Event[UUID] {
  override def id = UUID.randomUUID
  override def commandId = aCommandId
  override def occurredOn = Instant.now
}
