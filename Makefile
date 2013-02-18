
# Makefile for BugChipper

all:
	javac `find . -name '*.java'`

run:
	export CLASSPATH=/usr/share/java/mysql-connector-java.jar
	java BugChipper

clean:
	rm `find . -name '*.class'`

