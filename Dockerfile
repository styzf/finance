FROM openjdk:8

ARG JAR_PATH=./target

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone

ADD ${JAR_PATH}/*.jar /usr/src/myapp/ROOT.jar
WORKDIR /usr/src/myapp

CMD ["java", "-Duser.timezone=UTC", "-Xmx512m","-jar","/usr/src/myapp/ROOT.jar"]
