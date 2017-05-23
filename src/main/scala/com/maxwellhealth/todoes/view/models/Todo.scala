package com.maxwellhealth.todoes.view.models

import java.util.UUID
import java.time.Instant

final case class Todo(id: UUID, text: String, due: Instant, created: Instant, modified: Instant)
