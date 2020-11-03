package com.mongo

import java.util.Date
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonWriter}
import org.joda.time._
import org.mongodb.scala.MongoClient
import scala.language.implicitConversions

object MongoCodecs {

  protected val customCodecRegistry = CodecRegistries.fromCodecs(new BigDecimalCodec, new JodaDateTimeCodec, new JodaLocalDateCodec, new JavaDateCodec)
  val codecRegistry =
    CodecRegistries.fromRegistries(MongoClient.DEFAULT_CODEC_REGISTRY, com.mongodb.MongoClient.getDefaultCodecRegistry, customCodecRegistry)

  class JavaDateCodec extends Codec[Date] {
    override def encode(writer: BsonWriter, value: Date, encoderContext: EncoderContext): Unit = {
      writer.writeDateTime(value.getTime)
    }

    override def getEncoderClass: Class[Date] = classOf[Date]

    override def decode(reader: BsonReader, decoderContext: DecoderContext): Date = {
      new Date(reader.readDateTime())
    }
  }

  class JodaDateTimeCodec extends Codec[DateTime] {
    override def encode(writer: BsonWriter, value: DateTime, encoderContext: EncoderContext): Unit = {
      writer.writeDateTime(value.getMillis)
    }

    override def getEncoderClass: Class[DateTime] = classOf[DateTime]

    override def decode(reader: BsonReader, decoderContext: DecoderContext): DateTime = {
      new DateTime(reader.readDateTime())
    }
  }

  class JodaLocalDateCodec extends Codec[LocalDate] {
    override def encode(writer: BsonWriter, value: LocalDate, encoderContext: EncoderContext): Unit = {
      writer.writeDateTime(value.toDateTimeAtStartOfDay.getMillis)
    }

    override def getEncoderClass: Class[LocalDate] = classOf[LocalDate]

    override def decode(reader: BsonReader, decoderContext: DecoderContext): LocalDate = {
      new DateTime(reader.readDateTime()).toLocalDate
    }
  }

  class BigDecimalCodec extends Codec[BigDecimal]{
    override def encode(writer: BsonWriter, value: BigDecimal, encoderContext: EncoderContext): Unit = {
      writer.writeDouble(value.doubleValue)
    }

    override def getEncoderClass: Class[BigDecimal] = classOf[BigDecimal]

    override def decode(reader: BsonReader, decoderContext: DecoderContext): BigDecimal = {
      BigDecimal(reader.readDouble())
    }
  }

}