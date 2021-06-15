FROM adoptopenjdk:8-jdk

RUN apt-get update

RUN apt-get install wget procps -y

WORKDIR /tmp

RUN wget https://mirrors.sonic.net/apache//directory/apacheds/dist/2.0.0.AM26/apacheds-2.0.0.AM26-amd64.deb

RUN chmod +x apacheds-2.0.0.AM26-amd64.deb

RUN dpkg -i apacheds-2.0.0.AM26-amd64.deb

RUN mv /etc/init.d/apacheds-2.0.0.AM26-default /etc/init.d/apacheds

RUN sed -i 's/\# wrapper.java.command.*/wrapper.java.command=\/opt\/java\/openjdk\/bin\/java/g' /opt/apacheds-2.0.0.AM26/conf/wrapper.conf

RUN service apacheds restart

EXPOSE 10389 10636

CMD tail -f /dev/null
