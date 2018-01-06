package com.botchaa.$name;format="word"$

import com.botchaa.httpcommons.http.AkkaHttpService
import com.typesafe.scalalogging.LazyLogging

object $name;format="Camel"$ extends AkkaHttpService(BuildInfo.name) with LazyLogging {

  private val routes = new Routes()

  def main(args: Array[String]): Unit = runService(Config.toString) {
    logger.info("Successfully initialized database connectivity...")
    routes.start()
  }

}
