package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaInspections

object Build : BuildType({
    name = "Build and Verify"

    publishArtifacts = PublishMode.SUCCESSFUL
    vcs {
        root(SvnHttpSubversionSvnMovieServiceTrunk)
    }
    steps {
        maven {
            goals = "clean verify"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            userSettingsSelection = "maven-global-settings.xml"
            jdkHome = "%env.JDK_11_0_x64%"
            coverageEngine = idea {
                includeClasses = "com.example.*"
                excludeClasses = "com.example.movie.*Test.java"
            }
            param("teamcity.coverage.jacoco.classpath", "+:target/classes/**/*.class")
        }
        ideaInspections {
            pathToProject = "pom.xml"
            jvmArgs = "-Xmx512m -XX:ReservedCodeCacheSize=240m"
            targetJdkHome = "%env.JDK_18%"
        }
        step {
            name = "Sonar Scan"
            type = "sonar-plugin"
            param("sonarProjectSources", "src/main")
            param("sonarProjectName", "Movie Service")
            param("target.jdk.home", "%env.JDK_11_0_x64%")
            param("sonarProjectTests", "src/test")
            param("teamcity.tool.sonarquberunner", "%teamcity.tool.sonar-qube-scanner.4.2.0.1873-scanner%")
            param("additionalParameters", "-Dsonar.scm.disabled=true")
            param("sonarProjectKey", "movie-service")
            param("sonarProjectBinaries", "target/classes")
            param("sonarServer", "bdfb31b4-0c55-491a-8848-ba0e313c886a")
        }
    }
})
