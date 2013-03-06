
# Makefile for BugChipper

all:
	export CLASSPATH=./db4o/db4o-8.0.249.16098-all-java5.jar:./
	javac `find . -name '*.java'`

run:
	java bcclient/BugChipper

jar:
	jar cfm BugChipper.jar Manifest_client.txt bcclient/ db4o/
	jar cfm BugChipperServer.jar Manifest_server.txt bcserver/ db4o/
	chmod +x *.jar

runjar:
	java -jar BugChipper.jar

clean:
	rm `find . -name '*.class'`

