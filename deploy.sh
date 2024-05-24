#!/usr/bin/env bash

mvn clean package
scp -P 2222 target/*.jar s311680@se.ifmo.ru:~