package com.maxwellhealth.todoes.domain.events

import com.maxwellhealth.sourcery.Event
import java.time.Instant
import java.util.UUID

case class TodoWasCreated(aCommandId: UUID, theEntityId: UUID, text: String, due: Instant) extends Event[UUID] {
  def id = UUID.randomUUID
  def commandId = aCommandId
  def entityId = theEntityId
  def occurredOn = Instant.now
}
