#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2

echo "> $REPOSITORY 디렉터리로 이동"
cd $REPOSITORY

echo "> 배포 파일 복사"
cp $REPOSITORY/zip/*.war $REPOSITORY/

echo "> 구동 중인 애플리케이션 PID 검색"
CURRENT_PID=$(pgrep -fl 'springboot*' | grep war | awk '{ print $1 }')

echo "> 구동 중인 애플리케이션 PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없습니다."
else 
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

JAR_NAME=$(ls -tr $REPOSITORY/ | grep war | tail -n 1)
echo "> 새 애플리케이션($JAR_NAME) 배포"
nohup java -jar \
	-Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties -Dspring.profiles.active=real \
	$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &