FROM hub.c.163.com/library/java:8-alpine

MAINTAINER noly 1346735074@qq.com

ADD elasticjob-demo/target/*.jar elasticjob-demo.jar

EXPOSE 7783

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "/elasticjob-demo.jar"]