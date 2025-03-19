

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
    "dev.zio" %% "zio" % "2.1.16",
    "dev.zio" %% "zio-streams" % "2.1.16"))