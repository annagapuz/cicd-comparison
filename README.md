# Compare Local Installation CI/CD Tools

## Requirements (in order of priority)
- Installation is compatible with RHEL
  - Prefer robust patching and update cycles, ideally aligned with RHEL patching
- Actively developed with a large community and plenty of documentation
- Integrates with current ecosystem
  - Rich plugin and integration marketplace
- Easier learning curve
- Option for enterprise-class premium support
- Compatible with cloud and/or container ecosystems 

## CI / CD Tools
### Jenkins
### GitLab CICD
### JetBrains TeamCity
### GoCD
### CircleCI
### Azure DevOps Server
### Oracle Container Pipelines

## Ecosystem
### RHEL 8
https://developers.redhat.com/blog/2020/03/24/red-hat-universal-base-images-for-docker-users/
### Windows Server
Will exclude from demo
### Ansible
Will replace provisioning with Docker and Docker Compose
https://www.redhat.com/en/blog/integrating-ansible-jenkins-cicd-process
https://github.com/ricardozanini/soccer-stats/blob/master/Jenkinsfile
### Subversion
### Nexus
https://www.sonatype.com/nexus/free-developer-tools
### JBOSS
### Sybase
### MSSQL
Will exclude from demo
### Veracode
Will replace with Sonarqube
### CAST
Will exclude from demo

## Tasks
- Docker containers (w/ compose)
  - CICD with Ansible (1 per tool)
  - Subversion
  - SonarQube
  - Nexus and OSS Scan
  - Sybase
  - JBOSS x2
  - Box for scripts? RESTful client? Reporting?
- Create simple RESTful service
- Create simple UI
- Pull from Subversion
- Run maven build
- Run static code analysis
  - PMD, FindBugs, SonarQube, EclEmma (JaCoCo)
- Deploy to Nexus
- OSS index scan (?)
- Ansible or RPM?
  - Create directories and copy (properties) files via RPM
  - Deploy war file to JBOSS server
- Ansible
    - Verify service is running
