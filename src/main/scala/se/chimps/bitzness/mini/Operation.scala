package se.chimps.bitzness.mini

trait Operation[T] {
	def map[K](func:(T)=>K):Operation[K] = {
		this match {
			case Result(data) => Result(func(data))
			case p:Problem[T] => Problem(p.error, p.metadata)
		}
	}

	def flatMap[K](func:(T)=>Operation[K]):Operation[K] = {
		this match {
			case Result(data) => func(data)
			case p:Problem[T] => Problem(p.error, p.metadata)
		}
	}

	def nonEmpty:Boolean = {
		this match {
			case r:Result[T] => true
			case _ => false
		}
	}

	def isProblem:Boolean = {
		!this.nonEmpty
	}

	def result:Option[T] = {
		this match {
			case Result(data) => Some(data)
			case _ => None
		}
	}
}

object Operation {
	def apply[T](func:() => Operation[T]) = func()
	def apply[T](data:T) = Result(data)
}

case class Result[T](data:T) extends Operation[T]
case class Problem[T](error:String, metadata:Map[String, String] = Map()) extends Operation[T]