package se.chimps.bitzness.mini.hmac

import com.roundeights.hasher.Implicits._

trait Hmac {

	def signString(key:String, data:String):String = data.hmac(key).sha256.hex
	def signBytes(key:String, data:Array[Byte]):String = data.hmac(key).sha256.hex
	def verifyString(key:String, data:String, hash:String):Boolean = data.hmac(key).sha256.hash_=(hash)
	def verifyBytes(key:String, data:Array[Byte], hash:String):Boolean = data.hmac(key).sha256.hash_=(hash)

}
