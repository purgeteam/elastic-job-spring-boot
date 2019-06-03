#!/usr/bin/env bash

docker pull zookeeper

docker run --privileged=true --name zookeeper --publish 2181:2181 -d zookeeper:latest