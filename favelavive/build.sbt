import com.typesafe.config.ConfigFactory
import play.ebean.sbt.PlayEbean

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
name := conf.getString("play.app.name")
version := conf.getString("play.app.version")

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

sources in (Compile, doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  filters,
  evolutions,
  javaWs,
  "org.codehaus.jettison" % "jettison" % "1.3.3",
  "org.postgresql" % "postgresql" % "9.4.1208",
  "be.objectify" %% "deadbolt-java" % "2.5.0",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "br.com.moip" % "java-sdk" % "2.1.2"
)

resolvers +=  Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)

resolvers +=  Resolver.url("Objectify Play Snapshots Repository", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns)

doc in Compile <<= target.map(_ / "none")

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
