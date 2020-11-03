package com.mongo

import org.mongodb.scala._
import salat.Context
import salat.json.{JSONConfig, StrictJSONDateStrategy}

object Mongod {
  val mongoClient: MongoClient = MongoClient("")
  def withDb(database: Option[String] = None) = mongoClient.getDatabase(database.getOrElse("local"))
}

trait Mongod {
  def withDb(database: Option[String] = None) = Mongod.withDb(database).withCodecRegistry(MongoCodecs.codecRegistry)
}