# Set up Demo

- Start all docker-compose services
  - docker-compose up -d 
- Check in all service code to SVN
  - <do on Mac so it's on Docker volume>
  - http://localhost:9080/svn/movie-service
    - svn import -m "Initial checkin" http://localhost:9080/svn/movie-service/trunk
    - svn co http://localhost:9080/svn/movie-service/trunk SvnMovieService
  - http://localhost:9080/svn/movie-web
    - svn import -m "Initial checkin" http://localhost:9080/svn/movie-web/trunk
    - svn co http://localhost:9080/svn/movie-web/trunk SvnMovieWeb
  - http://localhost:9080/svn/movie-scripts
    - svn import -m "Initial checkin" http://localhost:9080/svn/movie-scripts/trunk
    - svn co http://localhost:9080/svn/movie-service/trunk SvnMovieScripts
  - http://localhost:9080/svn/pipeline
    - svn import -m "Initial checkin" http://localhost:9080/svn/pipeline/trunk
    - svn co http://localhost:9080/svn/pipeline/trunk SvnPipeline
- JBoss setup
  - Admin console - http://localhost:9900
    - Add ENVIRONMENT=deploy to Jboss System Properties
  - Service - http://localhost:9800/movie-service
  - Web - http://localhost:9800/movie-web
- Nexus
  - docker exec -it docker_nexus_1 bash 
    - cat /nexus-data/admin.password
  - Add Releases and Snapshots repositories
  - Add Sybase driver to Nexus
    - mvn deploy:deploy-file -DrepositoryId=demoRepo -Durl=http://localhost:9081/repository/demo-release/ -Dfile=/home/amgaps/dev/software/sap-ase/jconn4.jar -DgroupId=com.sybase.jdbc4 -DartifactId=jconn -Dversion=4 -Dpackaging=jar
- SonarQube
  - Start as admin:admin
  - mvn sonar:sonar -Dsonar.projectKey=movie-service -Dsonar.host.url=http://sonarqube:9000 -Dsonar.login=07b739a25d86dc8f9c07ecc68b9a28aa495933e4 -Dsonar.scm.disabled=true
- Jenkins
  - Install Plugins
    - JaCoCo
    - SonarQube
  - Install JDK
    - https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz
  - Install Maven
    - Add global settings.xml
  ```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>demoRepo</id>
      <username>admin</username>
      <password>password</password>
    </server>
  </servers>

  <!-- mirrors
   | This is a list of mirrors to be used in downloading artifacts from remote repositories.
   |
   | It works like this: a POM may declare a repository to use in resolving certain artifacts.
   | However, this repository may have problems with heavy traffic at times, so people have mirrored
   | it to several places.
   |
   | That repository definition will have a unique id, so we can create a mirror reference for that
   | repository, to be used as an alternate download site. The mirror site will be the preferred
   | server for that repository.
   |-->
  <mirrors>
    <!-- mirror
     | Specifies a repository mirror site to use instead of a given repository. The repository that
     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
     |
    <mirror>
      <id>mirrorId</id>
      <mirrorOf>repositoryId</mirrorOf>
      <name>Human Readable Name for this Mirror.</name>
      <url>http://my.repository.com/repo/path</url>
    </mirror>
     -->
  </mirrors>

  <profiles>

    <profile>
         <repositories>
            <repository>
               <snapshots>
                  <enabled>false</enabled>
               </snapshots>
               <id>central</id>
               <name>libs-release</name>
               <url>https://repo1.maven.org/maven2/</url>
            </repository>
            <repository>
               <snapshots />
               <id>snapshots</id>
               <name>libs-snapshot</name>
               <url>https://repo1.maven.org/maven2/</url>
            </repository>
         </repositories>
         <pluginRepositories>
            <pluginRepository>
               <snapshots>
                  <enabled>false</enabled>
               </snapshots>
               <id>central</id>
               <name>plugins-release</name>
               <url>https://repo1.maven.org/maven2/</url>
            </pluginRepository>
            <pluginRepository>
               <snapshots />
               <id>snapshots</id>
               <name>plugins-snapshot</name>
               <url>https://repo1.maven.org/maven2/</url>
            </pluginRepository>
         </pluginRepositories>
         <id>nexus</id>
      </profile>
      
      <profile>
         <repositories>
            <repository>
               <snapshots>
                  <enabled>true</enabled>
               </snapshots>
               <id>releases</id>
               <name>demo-release</name>
               <url>http://nexus:8081/repository/demo-release/</url>
            </repository>
            <repository>
               <snapshots />
               <id>snapshots</id>
               <name>demo-snapshot</name>
               <url>http://nexus:8081/repository/demo-snapshot/</url>
            </repository>
         </repositories>
         <id>demo</id>
      </profile>

  </profiles>

  <activeProfiles>
    <activeProfile>nexus</activeProfile>
    <activeProfile>demo</activeProfile>
  </activeProfiles>

</settings>
```
- TeamCity
  - http://localhost:8111
  - Authorize agent
