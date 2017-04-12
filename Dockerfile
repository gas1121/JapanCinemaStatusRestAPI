# docker container for application, include java, gradle and maven
FROM library/java:alpine

# use ustc mirrors
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories

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
  && wget --quiet http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.zip \
  && unzip apache-maven-${MAVEN_VERSION}-bin.zip \
  && rm apache-maven-${MAVEN_VERSION}-bin.zip

RUN apk del wget

RUN apk update && apk add bash libstdc++ && rm -rf /var/cache/apk/*

# mirror for maven
COPY docker-files/maven/settings.xml /usr/share/maven/conf/settings.xml

# run maven to generate cache
COPY . /app
WORKDIR /app
RUN mvn dependency:go-offline

# build and run application
ENTRYPOINT ["/bin/sh", "-c"]
CMD ["gradle bootRun"]
