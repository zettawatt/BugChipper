
# Makefile for BugChipper

all:
	javac `find . -name '*.java'`

run:
	java BugChipper

clean:
	rm `find . -name '*.class'`

