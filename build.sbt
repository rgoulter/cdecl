name := "c-decl-java"

version := "0.1.3"

/* scalaVersion := "2.11.2" */

antlr4Settings

antlr4GenListener in Antlr4 := true

antlr4GenVisitor in Antlr4 := true

// Unfortunately, the SBT plugin maintainer doesn't see
// file-path as a reliable way to define packagename.
antlr4PackageName in Antlr4 := Some("cdecl")

// Antlr4 4.5-RC
resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

antlr4Dependency in Antlr4 := "org.antlr" % "antlr4" % "4.5-SNAPSHOT"

libraryDependencies += "junit" % "junit" % "4.11"
