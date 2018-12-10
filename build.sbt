lazy val `fr-upem-easymow` = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "fr/upem",
      scalaVersion := "2.12.7",
      version := "1.2.7",
      scalacOptions += "-Ypartial-unification"
    )),
    name := "EasyMow",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
    libraryDependencies += "org.typelevel"   %% "cats-core"   % "1.4.0",
    libraryDependencies += "org.typelevel"    %% "cats-testkit" % "1.5.0" % Test
  )
