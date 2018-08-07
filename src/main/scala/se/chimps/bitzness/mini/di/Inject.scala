package se.chimps.bitzness.mini.di

import scala.collection.mutable

object Inject {
	private val objects = mutable.Map[String, AnyRef]()

	def apply(key:String, instance:AnyRef):Unit = objects.put(key, instance)

	def apply[T](key:String):T = objects(key).asInstanceOf[T]
}
