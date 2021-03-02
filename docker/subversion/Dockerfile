FROM registry.access.redhat.com/ubi8/ubi:8.3

# Copy repo so subversion can be installed
COPY wandisco-svn.repo /etc/yum.repos.d

RUN yum --disableplugin=subscription-manager repolist \
&& yum -y --disableplugin=subscription-manager update \
&& yum -y --disableplugin=subscription-manager install subversion \
&& yum -y --disableplugin=subscription-manager install mod_dav_svn \
&& yum -y --disableplugin=subscription-manager install httpd \
&& yum -y --disableplugin=subscription-manager install httpd-tools \
&& yum -y --disableplugin=subscription-manager clean all

COPY subversion.conf /etc/httpd/conf.d

WORKDIR /var/www/svn

RUN touch /etc/svn-users \
&& htpasswd -bm /etc/svn-users admin password \
&& chown root:apache /etc/svn-users \
&& chmod 640 /etc/svn-users

# Use a mono repo
RUN svnadmin create platform \
&& chown -R apache:apache platform

EXPOSE 80

CMD httpd -k restart -D FOREGROUND
