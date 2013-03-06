
# Makefile for BugChipper

all:
	export CLASSPATH=/home/chuck/projects/java/BugChipper/db4o/db4o-8.0.249.16098-all-java5.jar:./
	javac `find . -name '*.java'`

run:
	java BugChipper

jar:
	jar cfm BugChipper.jar Manifest.txt bugchipper/ *.class db4o/

runjar:
	java -jar BugChipper.jar

clean:
	rm `find . -name '*.class'`

