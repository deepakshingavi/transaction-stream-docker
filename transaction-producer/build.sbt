name := "transaction-producer-app"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.9"

/*
addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.18")

PB.targets in Compile := Seq(
  PB.gens.java -> (sourceManaged in Compile).value,
  scalapb.gen(javaConversions=true) -> (sourceManaged in Compile).value,
  scalapb.gen() -> (sourceManaged in Compile).value
)



libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.8.1"
libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.8.0" % "protobuf"

libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"*/
