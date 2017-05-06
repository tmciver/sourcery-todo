package com.maxwellhealth.todoes.domain
package object eventlisteners {

  import com.maxwellhealth.sourcery.EventListener
  import com.maxwellhealth.todoes.domain.events.TodoWasCreated
  import java.util.UUID

  def todoViewUpdater: EventListener[UUID] = {
    case TodoWasCreated(_, entityId, text, due) => println("Persisting Todo with ID: " + entityId + " to the Todo view table.") // should add a Todo to a Todo view table.
  }
}
