name := "bitzness-mini"

version := "20161217-1"

scalaVersion := "2.11.7"

organization := "se.chimps.bitzness"

credentials += Credentials(Path.userHome / ".ivy2" / ".bitzness")

publishTo := Some("se.chimps.bitzness" at "http://yamr.kodiak.se/maven")

publishArtifact in (Compile, packageSrc) := false

publishArtifact in (Compile, packageDoc) := false

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.4"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.4.4"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.4.4" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.10"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "de.neuland-bfi" % "jade4j" % "0.4.2"
