name := "cassandra-trainings"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  cache
)

libraryDependencies ++= Seq(
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.6",
  "com.datastax.cassandra" % "cassandra-driver-mapping" % "2.1.6",
  "org.springframework" % "spring-core" % "4.1.6.RELEASE",
  "org.springframework" % "spring-beans" % "4.1.6.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.6.RELEASE",
  "org.springframework" % "spring-context" % "4.1.6.RELEASE",
  "org.springframework.data" % "spring-data-cassandra" % "1.2.0.RELEASE"
)

