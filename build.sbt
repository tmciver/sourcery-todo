lazy val root = (project in file("."))
  .settings(
    organization := "com.timmciver",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.1",
    name := "todoes",
    libraryDependencies += "com.maxwellhealth" %% "sourcery" % "0.1.0-SNAPSHOT"
  )
