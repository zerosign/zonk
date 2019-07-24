import mill._
import scalalib._, scalafmt._
import modules.Assembly._

final object Globals {

  @inline final val version : String =
    os.proc('git, 'describe, "--tags").call().out.string.strip

}

object packages {

  lazy val versions = Map(
    "http4s"  -> "0.20.0-M6",
    "circe" -> "0.11.1",
    "log4s" -> "1.7.0",
    "logback" -> "1.3.0-alpha4",
    "logstash" -> "5.3",
  )

  final val cats-effect = Seq(
    ivy""
  )

  final val fs2 = Seq(
    ivy""
  )

  final val `http-client` = Seq(
    ivy"org.http4s::http4s-core:${versions("http4s")}",
    ivy"org.http4s::http4s-blaze-client:${versions("http4s"}",
  )

  final val serde = Seq(
    ivy"io.circe::circe-core:${versions("circe")}"
  )

  final val logging = Seq(
    ivy"org.log4s::log4s:1.7.0",
    ivy"ch.qos.logback:logback-core:1.3.0-alpha4",
    ivy"ch.qos.logback:logback-classic:1.3.0-alpha4",
    ivy"net.logstash.logback:logstash-logback-encoder:5.3"
  )

}

object webhdfs extends ScalaModule with BuildInfo with ScalafmtModule {

  @inline final def publishVersion = Globals.version

  @inline final def scalaVersion = "2.13.0"

  @inline final def scalacOptions = Seq(
    "-Ypartial-unification",
    "-target:jvm-1.8",
    "-encoding", "UTF-8",
    "-deprecation",
    "-Xexperimental",
    "-unchecked", "-opt-warnings",
    "-feature",
    "-language:higherKinds",
  )

  @inline final def buildInfoMembers == T[Map[String, String]] = T {
    Map(
      "version" -> publishVersion,
      "scalaVersion" -> scalaVersion()
    )
  }
}
