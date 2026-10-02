@echo off
if exist out rmdir /s /q out
if exist app.jar del app.jar
javac -d out src/papka/laba02/*.java
jar -cvfm app.jar manifest.txt -C out .
java -jar app.jar