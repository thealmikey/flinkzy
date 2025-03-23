val FlinkVersion = "1.17.0"

ThisBuild / scalaVersion := "3.6.4"

// enablePlugins(

//   ZioSbtEcosystemPlugin,
//   ZioSbtCiPlugin
// )
lazy val root = project
  .in(file("."))
  .settings(
    name := "Flinkzy",
    version := "0.1",
    libraryDependencies ++= Seq(
      "org.flinkextended" %% "flink-scala-api" % "1.18.1_1.2.1",
      "dev.zio" %% "zio" % "2.1.16",
      "dev.zio" %% "zio-http" % "3.1.0",
      "dev.zio" %% "zio-streams" % "2.1.16"
    )
  )
