javac -g -nowarn -encoding utf8 Client.java
jar cfm Client.jar Client.MF *.class
start java -jar Client.jar
timeout 5
del *.class

//start java -cp mysql-connector-java-5.1.39-bin.jar; Client



