package se.chimps.bitzness.mini.jooby

import org.jooby.{Request, Response}

import scala.concurrent.{ExecutionContext, Future}
import Futures._
import org.jooby.Route.{Handler, OneArgHandler, ZeroArgHandler}

import scala.concurrent.duration.Duration

trait ScalaFutures {
	def future(func:() => Future[Response])(implicit ec:ExecutionContext, duration:Duration):ZeroArgHandler = zeroArg(func)
	def future(func:(Request) => Future[Response])(implicit ec:ExecutionContext, duration:Duration):OneArgHandler = oneArg(func)
	def future(func:(Request, Response) => Unit)(implicit ec:ExecutionContext, duration:Duration):Handler = handler(func)
}
