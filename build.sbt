name := "bitzness-mini"

version := "20170930"

scalaVersion := "2.12.1"

organization := "se.chimps.bitzness"

credentials += Credentials(Path.userHome / ".ivy2" / ".bitzness")

publishTo := Some("se.chimps.bitzness" at "http://yamr.kodiak.se/maven")

publishArtifact in (Compile, packageDoc) := false

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.4"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.5.4"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.3"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "de.neuland-bfi" % "jade4j" % "0.4.2"
