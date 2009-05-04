#!/bin/sh
mvn package
java -cp target/rmiage-1.0-SNAPSHOT.jar rmiage.server.controller.MainController --config-file=example-config.properties
