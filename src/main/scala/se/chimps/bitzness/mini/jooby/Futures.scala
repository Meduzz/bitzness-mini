package se.chimps.bitzness.mini.jooby

import org.jooby.{Request, Response}
import org.jooby.Route.{Handler, OneArgHandler, ZeroArgHandler}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Futures {
	def zeroArg(func:() => Future[Response])(implicit ec:ExecutionContext, duration:Duration):ZeroArgHandler = () => Await.result(func(), duration)

	def oneArg(func:(Request) => Future[Response])(implicit ec:ExecutionContext, duration:Duration):OneArgHandler = (req:Request) => Await.result(func(req), duration)

	def handler(func:(Request, Response) => Unit)(implicit ec:ExecutionContext, duration:Duration):Handler = (req:Request, rsp:Response) => func(req, rsp)
}
