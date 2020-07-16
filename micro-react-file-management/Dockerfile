# 可以搭建容器，会有读取不到图片的问题，暂时没有解决，下次有机会再弄
FROM java:8
MAINTAINER yuhao5
ADD micro-react-file-management-1.0-SNAPSHOT.jar micro-react-file-management-1.0-SNAPSHOT.jar
RUN bash -c 'touch /micro-react-file-management-1.0-SNAPSHOT.jar'
RUN bash -c 'mkdir /images'

ENV SPRING-CLOUD-MS-NAME micro-react-file-management
ENV SPRING-CLOUD-HOSTNAME ubuntu
ENV TX-MANAGER-URL http://ubuntu:9101

ENTRYPOINT ["java","-jar","-Xmx1024m","-Xms1024m","/micro-react-file-management-1.0-SNAPSHOT.jar"]
