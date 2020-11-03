package com.mongo

import org.mongodb.scala._

object Mongod {
  val mongoClient: MongoClient = MongoClient("")

  def withDb(database: Option[String] = None) = mongoClient.getDatabase(database.getOrElse("local"))
}

trait Mongod {
  def withDb(database: Option[String] = None) = Mongod.withDb(database).withCodecRegistry(MongoCodecs.codecRegistry)

}
