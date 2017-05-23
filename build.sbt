lazy val root = (project in file("."))
  .settings(
    organization := "com.timmciver",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.1",
    name := "todoes",
    mainClass := Some("com.maxwellhealth.todoes.App"),

    libraryDependencies ++= {
      val akkaHttpVersion   = "10.0.6"

      Seq(
        "com.maxwellhealth" %% "sourcery" % "0.1.0-SNAPSHOT",
        "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
      )
    }
  )
