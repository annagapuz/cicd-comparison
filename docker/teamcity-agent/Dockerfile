FROM jetbrains/teamcity-agent:2020.2.2

USER root

# Install OpenJDK 11
RUN mkdir -p /usr/local/java && \
curl -s -X GET 'https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz' --output /tmp/openjdk-11.tar.gz && \
tar xf /tmp/openjdk-11.tar.gz -C /usr/local/java