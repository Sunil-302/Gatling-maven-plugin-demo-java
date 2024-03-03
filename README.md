Gatling plugin for Maven - Java demo project
============================================

A simple showcase of a Maven project using the Gatling plugin for Maven. Refer to the plugin documentation
[on the Gatling website](https://gatling.io/docs/current/extensions/maven_plugin/) for usage.

This project is written in Java, others are available for [Kotlin](https://github.com/gatling/gatling-maven-plugin-demo-kotlin)
and [Scala](https://github.com/gatling/gatling-maven-plugin-demo-scala).

It includes:

* [Maven Wrapper](https://maven.apache.org/wrapper/), so that you can immediately run Maven with `./mvnw` without having
  to install it on your computer
* minimal `pom.xml`
* latest version of `io.gatling:gatling-maven-plugin` applied
* sample [Simulation](https://gatling.io/docs/gatling/reference/current/general/concepts/#simulation) class,
  demonstrating sufficient Gatling functionality
* proper source file layout

*Running INDIVIDUAL scripts

	mvn gatling:test -D"gatling.simulationClass=computerdatabase.API.API_Test_feeders_03"

*Running ALL scripts

	mvn gatling:test

*Execute Windows batch command for JENKINS (code in LOCAL)


	d:
	cd D:\SoftwareEngineer\PerformanceTesting\Gatling\GatlingJavaProject\O-	gatling-maven-plugin-demo-java
	mvn gatling:test -	Dgatling.simulationClass=computerdatabase.API.API_Test_feeders_03

