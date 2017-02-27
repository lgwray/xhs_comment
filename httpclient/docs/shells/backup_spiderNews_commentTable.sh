#!/bin/bash
HOSTNAME=rdsqswhmjfiys87frepua.mysql.rds.aliyuncs.com
PORT=3306
DATABASE=spider_news
USERNAME=shihe
PASSWORD=shinc123456
TABLE_NAME=sh_jnl_article_comment
NOW=$(date +"%Y-%m-%d")
NOW_MONTH=$(date +"%Y_%m")
#MONTH=2016_02
#Linux date
MONTH=$(date -d "`date +%Y%m`01 -1 month" +%Y_%m)
#mac date
#MONTH=$(date -v -1m +%Y_%m) 
NEW_TABLE_NAME=${TABLE_NAME}_${MONTH}
SQLFILE=${TABLE_NAME}_${NOW}.sql
LOGFILE=/home/log/$0.log

BASE_CONN="-h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DATABASE}" 
create_tbl_sql="create table ${NEW_TABLE_NAME} like ${TABLE_NAME}"
columns="id,article_id,adddate,content,content_type,user_id,comment_way,nickname,send_flag,sendtime,md5"
move_sql="insert into ${NEW_TABLE_NAME}(${columns}) select ${columns} from ${TABLE_NAME} where date_format(adddate,'%Y_%m')='${MONTH}';"
delete_sql="delete from ${TABLE_NAME} where date_format(adddate,'%Y_%m')='${MONTH}'";

function tips() {
	echo "
		********************************************************
		*                                                      *
		*                 Backup xhs comment                   *
		*                     by gsj                           *
		*                                                      *
		********************************************************
		" >> ${LOGFILE} 2>&1
    echo "Begin time >> " `date` >>${LOGFILE} 2>&1
}

function createTable(){
    v=$(mysql ${BASE_CONN} -e "select count(id) num from ${NEW_TABLE_NAME}") 
    if [ !$v >> ${LOGFILE} 2>&1 ];then 
        mysql ${BASE_CONN} -e"${create_tbl_sql}" >> ${LOGFILE} 2>&1
        echo `date` "table ${NEW_TABLE_NAME} create success" >> ${LOGFILE} 2>&1
    else 
        echo `date` "table ${NEW_TABLE_NAME} has exist" >> ${LOGFILE} 2>&1
    fi
}

function moveData(){
    mysql ${BASE_CONN} -e"BEGIN" >> ${LOGFILE} 2>&1
    echo `date` "START TRANSACTION" >> ${LOGFILE} 2>&1
    mysql ${BASE_CONN} -e"${move_sql}" >> ${LOGFILE} 2>&1
    echo `date` "${move_sql}" >> ${LOGFILE} 2>&1
    mysql ${BASE_CONN} -e"${delete_sql}" >> ${LOGFILE} 2>&1
    echo `date` "${delete_sql}" >> ${LOGFILE} 2>&1
    mysql ${BASE_CONN} -e"COMMIT" >> ${LOGFILE} 2>&1
    echo `date` "COMMIT" >> ${LOGFILE} 2>&1
}

tips
createTable
moveData
echo "End time >> " `date` >>${LOGFILE} 2>&1
