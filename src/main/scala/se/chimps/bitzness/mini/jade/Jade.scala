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
	def render():Array[Byte]
}

class Jade4JImpl(template:JadeTemplate, model:Map[String, AnyRef], config:Option[JadeConfiguration] = None) extends Jade4j {
	import scala.collection.JavaConversions._

	override def render(): Array[Byte] = {
		if (config.nonEmpty) {
			config.get.renderTemplate(template, model).getBytes("utf-8")
		} else {
			Jade4J.render(template, model, true).getBytes("utf-8")
		}
	}
}