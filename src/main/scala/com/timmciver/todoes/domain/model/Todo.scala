package com.maxwellhealth.todoes.domain.model

import com.maxwellhealth.sourcery.Entity
import java.time.Instant
import java.util.UUID

case class Todo(id: UUID, text: String, due: Instant) extends Entity[UUID]
