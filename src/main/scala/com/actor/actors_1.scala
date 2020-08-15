package com.actor

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

/*
  The Actor Model provides a higher level of abstraction for writing concurrent and distributed systems.
  It alleviates the developer from having to deal with explicit locking and thread management, making it
  easier to write correct concurrent and parallel systems.

  Actors are objects which encapsulate state and behavior, they communicate exclusively by exchanging messages which are
  placed into the recipient’s mailbox.

  In a sense, actors are the most stringent form of object-oriented programming, but it serves better to view them as
  persons: while modeling a solution with actors, envision a group of people and assign sub-tasks to them, arrange their
  functions into an organizational structure and think about how to escalate failure.

  An ActorSystem is a heavyweight structure that will allocate 1…N Threads, so create one per logical application.
*/


object HelloWorld {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    message.replyTo ! Greeted(message.whom, context.self)
    Behaviors.same
  }
}
