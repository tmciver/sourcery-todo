package com.maxwellhealth.todoes.domain
package object events {

  import java.util.UUID
  import com.maxwellhealth.sourcery._
  import com.maxwellhealth.todoes.domain.eventlisteners._

  val eventListeners: EventListeners[UUID] = Map(classOf[TodoWasCreated] -> List(todoViewUpdater))
}
