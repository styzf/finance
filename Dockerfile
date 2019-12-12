FROM tomcat

ARG JAR_PATH=./target

RUN rm -rf /usr/local/tomcat/webapps/ROOT \
    rm -rf /usr/local/tomcat/conf/server.xml \
    && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone

ADD ${JAR_PATH}/*.war /usr/local/tomcat/webapps/ROOT.war

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]

EXPOSE 8080
