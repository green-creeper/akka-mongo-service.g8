package com.botchaa.$name;format="word"$

import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus
import net.ceedubs.ficus.readers.ArbitraryTypeReader
import net.ceedubs.ficus.readers.namemappers.implicits

trait Config {

  import Ficus._
  import ArbitraryTypeReader._

  protected case class HttpConfig(interface: String, port: Int)
  protected case class DbConfig(interface: String, port: Int, database: String)

  private val config = ConfigFactory.load()
  protected val httpConfig: HttpConfig = config.as[HttpConfig]("http")
  protected val dbConfig: DbConfig = config.as[DbConfig]("db")
}

object Config extends Config
