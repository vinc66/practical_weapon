#!/bin/bash
echo "启动服务"

pid=$(ps -ef|grep weapon |grep -v grep | awk '{ print $2 }')
if [ ! -n $pid ]; then
	echo "原服务不存在"
else
	kill -9 $pid
	echo "关闭服务"+$pid
fi

rm -rf /app/code
mkdir -p /app/code

cd /app/code
git clone git@xxxxx/practical_weapon.git /app/code
mvn clean package

cd /app/code/target
java -jar weapon.jar >/dev/null 2>&1 &
echo "服务启动成功"