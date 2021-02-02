FROM registry.access.redhat.com/ubi8/ubi:8.3

# Copy repo so subversion can be installed
COPY wandisco-svn.repo /etc/yum.repos.d

COPY maven-global-settings.xml /etc/maven/settings.xml

RUN yum --disableplugin=subscription-manager repolist \
&& yum -y --disableplugin=subscription-manager update \
&& yum -y --disableplugin=subscription-manager install subversion \
&& yum -y --disableplugin=subscription-manager install java-11-openjdk-devel \
&& yum -y --disableplugin=subscription-manager install maven \
&& yum -y --disableplugin=subscription-manager install git \
&& yum -y --disableplugin=subscription-manager clean all

WORKDIR /opt/movie

ENV JAVA_HOME=/usr/lib/jvm/java-11

COPY startup.sh ./

ENTRYPOINT sh /opt/movie/startup.sh && tail -f /dev/null
