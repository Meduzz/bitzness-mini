package se.chimps.bitzness.mini.json

trait JSON {
	import org.json4s._
	import org.json4s.native.JsonMethods._
	import org.json4s.native.Serialization.write
	implicit private val formats = DefaultFormats

	def decode[T](json:String)(implicit manifest:Manifest[T]):T = parse(json).extract[T]
	def encode(instance:AnyRef):String =  write(instance)
	def withParsed[T](json:String)(func:(JValue=>T)):T = func(parse(json))
}
