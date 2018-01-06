package com.botchaa.$name;format="word"$

import java.time.LocalDateTime

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.handleExceptions
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.botchaa.httpcommons.routes.VersionRoutes
import com.typesafe.scalalogging.LazyLogging
import spray.json.DefaultJsonProtocol

import scala.concurrent.Future

class Routes(implicit actorSystem: ActorSystem) extends LazyLogging with SprayJsonSupport with DefaultJsonProtocol with VersionRoutes with Config {

  protected implicit val materializer: ActorMaterializer = ActorMaterializer()(actorSystem)
  protected implicit val ec = actorSystem.dispatcher
  private val servicesDispatcher = actorSystem.dispatchers.lookup("services-dispatcher")

  private val http = Http(actorSystem)

  def start(): Future[ServerBinding] = http.bindAndHandle(routes, httpConfig.interface, httpConfig.port)

  private val serviceVersion = ServiceVersion(
    builtAt = LocalDateTime.parse(BuildInfo.builtAt),
    scalaVersion = BuildInfo.scalaVersion,
    version = BuildInfo.version,
    name = BuildInfo.name
  )

  val routes = {
    versionRoute(serviceVersion)
  }
}
