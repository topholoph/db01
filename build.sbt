name := """db1"""
organization := "db1"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice

libraryDependencies ++= Seq(
  javaJdbc
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.30"
)
libraryDependencies += "com.h2database" % "h2" % "1.4.192"

libraryDependencies ++= Seq(
  javaJdbc
)