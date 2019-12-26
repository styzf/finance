FROM openjdk:8

ARG JAR_PATH=./target

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone

ADD ${JAR_PATH}/*.jar /home/ROOT.jar

CMD ["java -Duser.timezone=UTC -Xmx512m -jar /home/ROOT.jar", "run"]

