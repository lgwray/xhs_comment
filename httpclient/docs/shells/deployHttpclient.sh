#!/bin/bash
export tomcat=/opt/code/java/apache-tomcat-8.0.22

sh $tomcat/bin/shutdown.sh
sleep 5s
rm -rf $tomcat/webapps/httpclient*
cp /home/work/httpclient.war $tomcat/webapps/
date=`date +"%Y%m%d%T"`
cp /home/work/httpclient.war /opt/backup/httpclient.war.$date
sh $tomcat/bin/startup.sh httpclient
