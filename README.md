# Compare Local Installation CI/CD Tools

## Requirements (in order of priority)
* Installation is compatible with RHEL
  * Prefer robust patching and update cycles, ideally aligned with RHEL patching
* Actively developed with a large community and plenty of documentation
* Integrates with current ecosystem
  * Rich plugin and integration marketplace
* Easier learning curve
* Option for enterprise-class premium support
* Compatible with cloud and/or container ecosystems

## CI / CD Tools
### Selected Tools
* Jenkins
* JetBrains TeamCity

### Removed from Comparison
* GitLab CICD
* Atlassian Bamboo
* CircleCI
* GoCD
* Azure DevOps Server
* Oracle Container Pipelines

## Ecosystem
### RHEL 8
* https://developers.redhat.com/blog/2020/03/24/red-hat-universal-base-images-for-docker-users/
* https://www.redhat.com/en/blog/new-year-new-red-hat-enterprise-linux-programs-easier-ways-access-rhel
* https://catalog.redhat.com/software/containers/ubi8/5c647760bed8bd28d0e38f9f
### Windows Server
* Will exclude from demo
### Ansible
* Will replace provisioning with Docker and Docker Compose
* Will exclude from demo
* https://www.redhat.com/en/blog/integrating-ansible-jenkins-cicd-process
* https://github.com/ricardozanini/soccer-stats/blob/master/Jenkinsfile
Satellite intended to be a layer over RPMs in Nexus
### Subversion
* https://subversion.apache.org/packages.html#redhat
* Repositories:
  * movie-service
  * movie-web
  * pipeline
### Nexus
* https://www.sonatype.com/nexus/free-developer-tools
* https://hub.docker.com/r/sonatype/nexus3
### JBOSS
* https://docs.jboss.org/wildfly/plugins/maven/latest/deploy-mojo.html
* https://hub.docker.com/r/jboss/wildfly
### RPM
* Will exclude from demo
* https://linuxconfig.org/how-to-create-an-rpm-package
* https://rpm-packaging-guide.github.io/
### Active Directory
* Will exclude from demo
### Sybase
* Will use image previously built - https://github.com/annagapuz/docker-sap-ase-express
### MSSQL
* Will exclude from demo
### Veracode
* Will replace with Sonarqube
* https://docs.sonarqube.org/latest/setup/get-started-2-minutes/
* https://hub.docker.com/_/sonarqube/
### CAST
* Will exclude from demo
### Jenkins
* https://hub.docker.com/r/jenkins/jenkins
* http://vgaidarji.me/blog/2018/07/30/working-with-jenkinsfile-in-intellij-idea/
* https://www.jenkins.io/doc/book/blueocean/getting-started/
### TeamCity
* https://hub.docker.com/r/jetbrains/teamcity-server
* https://www.jetbrains.com/help/teamcity/predefined-build-parameters.html#Detecting+Java+on+Agent
### GitLab
* Will exclude from demo
* https://hub.docker.com/r/gitlab/gitlab-ce
* https://hub.docker.com/r/gitlab/gitlab-runner
* https://docs.gitlab.com/runner/register/index.html#docker

## Tasks
* RHEL Docker containers (w/ Compose)
  * CICD (1 per tool)
  * Subversion
  * SonarQube
  * Nexus and OSS Scan
  * Sybase
  * JBOSS
  * Box for scripts? RESTful client? Reporting?
* Create simple CRUD RESTful service
* Create simple UI to call GET
* Poll Subversion
* Run maven build
  * Run static code analysis
    * PMD, FindBugs, SonarQube, EclEmma (JaCoCo)
* Deploy all artifacts to Nexus
* Deploy war files to JBOSS server
