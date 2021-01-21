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
### Jenkins
### GitLab CICD
### CircleCI
### JetBrains TeamCity
### GoCD
### Azure DevOps Server
### Oracle Container Pipelines

## Ecosystem
### RHEL 8
* https://developers.redhat.com/blog/2020/03/24/red-hat-universal-base-images-for-docker-users/
* https://www.redhat.com/en/blog/new-year-new-red-hat-enterprise-linux-programs-easier-ways-access-rhel
### Windows Server
* Will exclude from demo
### Ansible
* Will replace provisioning with Docker and Docker Compose
* Will exclude from demo
* https://www.redhat.com/en/blog/integrating-ansible-jenkins-cicd-process
* https://github.com/ricardozanini/soccer-stats/blob/master/Jenkinsfile
Satellite intended to be a layer over RPMs in Nexus
### Subversion
### Nexus
https://www.sonatype.com/nexus/free-developer-tools
* Standard Release and Snapshot repositories
* YUM repository
### JBOSS
### Apache HTTPD
### Active Directory
### Sybase
### MSSQL
* Will exclude from demo
### Veracode
* Will replace with Sonarqube
### CAST
* Will exclude from demo

## Tasks
* RHEL Docker containers (w/ Compose)
  * CICD (1 per tool)
  * Subversion
  * SonarQube
  * Nexus and OSS Scan
  * Sybase
  * JBOSS x2
  * Box for scripts? RESTful client? Reporting?
* Create simple CRUD RESTful service
* Create simple UI to call GET
* Create RPM spec
  * Create directories and copy (properties) files
  * Set permissions
  * Copy shell scripts
    * Call RESTful API POST or PUT
  * Cronjobs (?)
  * Build in verification checks
* Poll Subversion
* Run maven build
  * Run static code analysis
    * PMD, FindBugs, SonarQube, EclEmma (JaCoCo)
* Build RPM
* Deploy all artifacts to Nexus
* OSS index scan (?)
* Deploy war file to JBOSS server
  * Health checks (?)
* Deploy RPM
