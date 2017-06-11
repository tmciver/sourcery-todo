package com.maxwellhealth.todoes.domain
package object eventlisteners {

  import com.maxwellhealth.sourcery.EventListener
  import com.maxwellhealth.todoes.domain.events.TodoWasCreated
  import com.maxwellhealth.todoes.view.models.Todo
  import com.maxwellhealth.todoes.view.models.TodoRepository
  import java.util.UUID
  import java.time.Instant

  def todoViewUpdater: EventListener[UUID] = {
    case TodoWasCreated(_, entityId, text, due) => TodoRepository.save(Todo(entityId, text, due, Instant.now, Instant.now))
  }
}
