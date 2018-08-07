package se.chimps.bitzness.mini

import se.chimps.bitzness.mini.service.Service

import scala.sys.ShutdownHookThread

trait Application {
	var services:List[Service] = List()

	def main(args:Array[String]):Unit = {
		run(args)

		services.foreach(_.start())

		ShutdownHookThread {
			services.foreach(_.stop())
		}
	}

	def run(args:Array[String]):Unit

	def register(service:Service):Unit = {
		services = services :+ service
	}
}
