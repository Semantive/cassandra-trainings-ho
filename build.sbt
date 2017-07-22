name := "cassandra-trainings"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  ehcache, guice
)

libraryDependencies ++= Seq(
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.3.0",
  "com.datastax.cassandra" % "cassandra-driver-mapping" % "3.3.0"
)

