name := "bitzness-mini"

version := "20180807"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

organization := "se.chimps.bitzness"

credentials += Credentials(Path.userHome / ".ivy2" / ".bitzness")

publishTo := Some("se.chimps.bitzness" at "https://yamr.kodiak.se/maven")

publishArtifact in (Compile, packageDoc) := false

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.9"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.5.9"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.9" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.3"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "de.neuland-bfi" % "jade4j" % "1.2.6"
