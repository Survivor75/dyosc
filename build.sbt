name := "dyosc"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.8",
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.1.1",
  "org.mongodb" %% "casbah" % "3.1.1",
  "com.github.salat" %% "salat" % "1.11.2" excludeAll(ExclusionRule(organization = "org.mongodb" ), ExclusionRule(organization = "org.json4s"))
  )