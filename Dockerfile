FROM library/java:alpine

# install gradle and maven
RUN mkdir /opt
RUN mkdir /opt/gradle
RUN mkdir /opt/maven

ENV GRADLE_VERSION 3.4.1
ENV GRADLE_HOME /opt/gradle/gradle-${GRADLE_VERSION}
ENV PATH ${PATH}:${GRADLE_HOME}/bin

ENV MAVEN_VERSION 3.5.0
ENV MAVEN_HOME /opt/maven/apache-maven-${MAVEN_VERSION}
ENV PATH ${PATH}:${MAVEN_HOME}/bin

# use wget to download pachage
RUN set -x \
  && apk add --no-cache wget

WORKDIR /opt/gradle
RUN set -x \
  && wget --quiet https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
  && unzip gradle-${GRADLE_VERSION}-bin.zip \
  && rm gradle-${GRADLE_VERSION}-bin.zip

WORKDIR /opt/maven
RUN set -x \
  && wget --quiet http://mirror.bit.edu.cn/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.zip \
  && unzip apache-maven-${MAVEN_VERSION}-bin.zip \
  && rm apache-maven-${MAVEN_VERSION}-bin.zip

RUN apk del wget

RUN apk update && apk add bash libstdc++ && rm -rf /var/cache/apk/*
