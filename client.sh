#!/bin/sh
mvn package
java -cp target/rmiage-1.0-SNAPSHOT.jar rmiage.client.controller.MainController
