#!/usr/bin/env bash
###################################
#     --=== 系统启动脚本 ===--      #
###################################
DIR_NAME=$0
if [ "${DIR_NAME:0:1}" = "/" ];then
    # shellcheck disable=SC2006
    DIR_PATH=`dirname "$DIR_NAME"`
else
    # shellcheck disable=SC2006
    # shellcheck disable=SC2123
    DIR_PATH="`pwd`"/"`dirname "$DIR_NAME"`"
fi
nohup "$DIR_PATH"/bin/java -jar "$DIR_PATH"/RENFEI.NET.jar > "$DIR_PATH"/RENFEI.NET.log 2>&1 & echo $! > "$DIR_PATH"/RENFEI.NET.pid & tail -f "$DIR_PATH"/RENFEI.NET.log