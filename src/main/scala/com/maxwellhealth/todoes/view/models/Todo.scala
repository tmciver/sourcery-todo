package com.maxwellhealth.todoes.view.models

import com.maxwellhealth.todoes.view.models.Todo
import java.util.UUID
import java.time.Instant

final case class Todo(id: UUID, text: String, due: Instant, created: Instant, modified: Instant)
