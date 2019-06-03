#!/usr/bin/env bash
ELK_VERSION=latest

#mvn clean package -Dmaven.test.skip=true -U
#docker login --username=15138067756 registry.cn-hangzhou.aliyuncs.com

docker build -t registry.cn-hangzhou.aliyuncs.com/engine/elasticjob-demo:$ELK_VERSION .

docker push registry.cn-hangzhou.aliyuncs.com/engine/elasticjob-demo:$ELK_VERSION

#docker run -e SPRING_PROFILES_ACTIVE="prod" -p 9999:9999 -d elk-engine:$ELK_VERSION




# 拉取
#docker pull registry.cn-hangzhou.aliyuncs.com/engine/elk-engine:$ELK_VERSION


# jar 启动
#java -jar /Volumes/CodeFile/GitHub/1346735074/elk-engine/es-scheduled/target/es-scheduled-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
