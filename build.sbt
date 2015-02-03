name := "c-decl"

organization := "edu.nus"

version := "0.1.0-SNAPSHOT"

mainClass in Compile := Some("cdecl.Drive")

antlr4Settings

antlr4GenListener in Antlr4 := true

antlr4GenVisitor in Antlr4 := true

antlr4PackageName in Antlr4 := Some("cdecl")

antlr4Dependency in Antlr4 := "org.antlr" % "antlr4" % "4.5"

libraryDependencies += "junit" % "junit" % "4.11"
