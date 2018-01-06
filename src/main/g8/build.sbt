import java.time.LocalDateTime

import sbt.Resolver

enablePlugins(JavaAppPackaging, BuildInfoPlugin)

name := """$name;format="normalize"$"""
organization := "$organization$"
version := "0.0.1"
scalaVersion := "2.12.4"

mainClass in Compile := Some("$organization$.$name;format="word"$.$name;format="Camel"$")

scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  lazy val akkaHttpVersion = "10.0.11"
  lazy val akkaVersion = "2.5.8"
  lazy val ficusV = "1.4.0"
  lazy val commonsV = "0.0.1"

  Seq(
    "com.botchaa" %% "httpcommons" % commonsV,
    "org.reactivemongo" %% "reactivemongo" % "0.12.6",
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.iheart" %% "ficus" % ficusV,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.0.1" % Test,
    "org.mockito" % "mockito-core" % "1.10.8" % Test
  )
}

topLevelDirectory := Some(name.value)

val BuiltAt = LocalDateTime.now()

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, BuildInfoKey.action("builtAt")(BuiltAt))

buildInfoPackage := "$organization$.$name;format="word"$"

initialCommands := """
import akka.actor._
import akka.pattern._
import akka.util._
import scala.concurrent._
import scala.concurrent.duration._
""".stripMargin

Defaults.itSettings

lazy val eventservice = project.in(file(".")).configs(IntegrationTest)
