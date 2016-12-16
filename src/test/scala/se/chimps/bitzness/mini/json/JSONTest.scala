package se.chimps.bitzness.mini.json

import org.scalatest.FunSuite

class JSONTest extends FunSuite with JSON {

	test("object can go into json and back") {
		val name = "åäö?!*≥"
		val subject = Simple(name)

		val json = encode(subject)
		assert(json != null, "the json string was null...")
		assert(json.nonEmpty, "the json string was empty.")
		assert(json.contains(name), "the json string does not contain name.")

		val reborn = decode[Simple](json)
		assert(reborn.name.equals(name), "the reborns name did not match the original name.")
		assert(reborn.equals(subject), "the reborn did not equal the subject.")
	}

	test("list of objects can go into json and back") {
		val subject = List(Simple("1"), Simple("2"))

		val json = encode(subject)
		assert(json != null, "the json string was null")
		assert(json.nonEmpty, "the json string was empty")

		val reborn = decode[List[Simple]](json)
		assert(reborn.size == 2, "the size of the reborn are incorrect.")
		assert(reborn.equals(subject), "the reborn does not equal the subject")
	}

}

case class Simple(name:String)
