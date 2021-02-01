FROM registry.access.redhat.com/ubi8/ubi:8.3

RUN yum --disableplugin=subscription-manager repolist \
&& yum -y --disableplugin=subscription-manager update \
&& yum -y --disableplugin=subscription-manager clean all

WORKDIR /var/movie/scripts

COPY addStreamCount.sh ./

ENTRYPOINT sh /var/movie/scripts/addStreamCount.sh