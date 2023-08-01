# WFLY-18300_reproducer
A simple reproducer for the WildFly Bug WFLY-18300

It uses the latest available WildFly version `29.0.0.Final`.

Note: You also need docker to run the reproducer.

## How to run the reproducer
1) Build everything by running `mvn clean install`
2) Start the otel-collector with: `sh start-collector.sh` 
3) Then you can run the server with: `java -jar server/target/server-local-SNAPSHOT-bootable.jar`
4) In the log of the collector you can find the error messages

## Description