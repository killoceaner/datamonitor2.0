#!/bin/bash

SITE='datamonitor'

find ./target/classes -name "*.xml"|xargs rm -f

tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/datamonitor-0.0.1-SNAPSHOT-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

JAVA_OPTS="-Xms256m -Xmx256m -Xmn128m"  

echo $CLASSPATH

java $JAVA_OPTS -DlogFilePath=${SITE} -classpath $CLASSPATH com.ossean.datamonitor.Monitor >log/${SITE}.log2>&1 &