#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=springboot-webservice

echo "> Build 파일을 복사"
echo "> cp $REPOSITORY/zip/*.war $REPOSITORY/"
cp $REPOSITORY/zip/*.war $REPOSITORY/

JAR_NAME=$(ls -tr $REPOSITORY/ | grep war | tail -n 1)
IDLE_PROFILE=$(find_idle_profile)

echo "> 새 애플리케이션($JAR_NAME) 배포"
nohup java -jar \
	-Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties -Dspring.profiles.active=$IDLE_PROFILE \
	$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
