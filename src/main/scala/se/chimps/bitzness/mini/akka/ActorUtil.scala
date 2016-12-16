package se.chimps.bitzness.mini.akka

import akka.actor.{Actor, ActorRef, ActorRefFactory, Props}
import akka.util.Timeout

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

trait ActorUtil {

	implicit def timeout:Timeout
	implicit def ec:ExecutionContext

	def context:ActorRefFactory

	def simple[T<:Actor](implicit classTag:ClassTag[T]):ActorRef = context.actorOf(Props(classTag.runtimeClass))

	def named[T<:Actor](name:String)(implicit classTag: ClassTag[T]):Future[ActorRef] = {
		context.actorSelection(name).resolveOne()
			.recover({
				case e:Throwable => context.actorOf(Props(classTag.runtimeClass), name)
			})
	}

	def factory[T<:Actor](factory:ActorFactory[T])(implicit classTag:ClassTag[T]):ActorRef = context.actorOf(Props(factory.actor), factory.name)

}
