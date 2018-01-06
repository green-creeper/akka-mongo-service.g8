package com.botchaa.$name;format="word"$.db

import com.botchaa.$name;format="word"$.Config
import reactivemongo.api.MongoDriver
import scala.concurrent.ExecutionContext.Implicits.global

object Database extends Config {

  val driver = new MongoDriver

  val connection = driver.connection(List(s"\${dbConfig.interface}:\${dbConfig.port}"))

  val db = connection.database(dbConfig.database)

}
