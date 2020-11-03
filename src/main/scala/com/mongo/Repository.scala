package com.mongo

import com.mongodb.BasicDBObject
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.Document
import salat.{CaseClass, Context, grater}
import org.bson.conversions.Bson
import org.bson.json.{JsonMode, JsonWriterSettings}
import org.mongodb.scala.bson.BsonDocument
import salat.json.{JSONConfig, StrictJSONDateStrategy}

object BsonUtils {
  implicit val salatContext = new Context {
    val name = "mongod-salat-context"
    override val  jsonConfig = JSONConfig().copy(dateStrategy = StrictJSONDateStrategy())
  }

  def toBson[T <: CaseClass : Manifest](entity: T): BsonDocument =
    grater[T].asDBObject(entity).asInstanceOf[BasicDBObject].toBsonDocument(classOf[Document], MongoCodecs.codecRegistry)

  def toEntity[T <: CaseClass : Manifest](document: BsonDocument): T =
    grater[T].asObject(new MongoDBObject(BasicDBObject.parse(document.toJson(new JsonWriterSettings(JsonMode.SHELL)))))

  def toEntity[T <: CaseClass : Manifest](document: Document): T = {
    grater[T].asObject(
      new MongoDBObject(
        BasicDBObject.parse(
          document.toJson(
            new JsonWriterSettings(JsonMode.SHELL)
          )
        )
      )
    )
  }

}

case class Pager(page: Int, size: Int = 20, sortBy : Option[String] = None)

trait Repository[T <: CaseClass] {
  def create(entity: T, idOpt : Option[String] = None): T
  def read(id: String): Option[T]
  def update(id: String, updates : Bson): Option[T]
  def update(query: Bson, updates : Bson): Long
  def replace(id: String, entity : T) : Option[T]
  def upsert(id: String, entity : T) : Option[T]
  def delete(id: String): Option[T]
  def delete(query: Bson): Long
  def find(criteria: Option[Bson] = None, paging: Option[Pager] = None): List[T]

}

class MongoRepository[T <: CaseClass : Manifest](collectionName: String, database : Option[String] = None) extends Repository[T] with Mongod {
  val collection = withDb(database).getCollection(collectionName)

  override def create(entity: T, idOpt: Option[String]): T = ???

  override def read(id: String): Option[T] = ???

  override def update(id: String, updates: Bson): Option[T] = ???

  override def update(query: Bson, updates: Bson): Long = ???

  override def replace(id: String, entity: T): Option[T] = ???

  override def upsert(id: String, entity: T): Option[T] = ???

  override def delete(id: String): Option[T] = ???

  override def delete(query: Bson): Long = ???

  override def find(criteria: Option[Bson], paging: Option[Pager]): List[T] = ???

}
