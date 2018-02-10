package se.chimps.bitzness.mini.jade

import java.io.Reader

import de.neuland.jade4j.{Jade4J, JadeConfiguration}
import de.neuland.jade4j.template._

object Jade4j {
	def apply(template:String, model:Map[String, AnyRef]=Map()):Jade4j = {
		new Jade4JImpl(Jade4J.getTemplate(template), model)
	}

	def file(basePath:String, template:String, model:Map[String, AnyRef]=Map()):Jade4j = {
		val loader = new FileTemplateLoader(basePath, "utf-8")
		forLoader(template, model, loader)
	}

	def classpath(template:String, model:Map[String, AnyRef]=Map()):Jade4j = {
		val loader = new ClasspathTemplateLoader()
		forLoader(template, model, loader)
	}

	def reader(reader:Reader, template:String, model:Map[String, AnyRef]=Map()):Jade4j = {
		val loader = new ReaderTemplateLoader(reader, template)
		forLoader(template, model, loader)
	}

	def forLoader(template:String, model:Map[String, AnyRef], loader:TemplateLoader):Jade4j = {
		val config = new JadeConfiguration()
		config.setTemplateLoader(loader)
		config.setCaching(false)
		config.setPrettyPrint(true)
		val jade = config.getTemplate(template)
		new Jade4JImpl(jade, model, Some(config))
	}
}

trait Jade4j {
	def render(encoding:String):Array[Byte]
	def renderString():String
}

class Jade4JImpl(template:JadeTemplate, model:Map[String, AnyRef], config:Option[JadeConfiguration] = None) extends Jade4j {
	import scala.collection.JavaConverters._

	override def render(encoding:String): Array[Byte] = {
		config match {
			case Some(c) => c.renderTemplate(template, model.asJava).getBytes(encoding)
			case None => Jade4J.render(template, model.asJava, true).getBytes(encoding)
		}
	}

	override def renderString():String = {
		config match {
			case Some(c) => c.renderTemplate(template, model.asJava)
			case None => Jade4J.render(template, model.asJava, true)
		}
	}
}