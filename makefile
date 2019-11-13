JDK = javac
JRE = java
CPATH = -cp po.jar:.

all: m19/app/App.java
	$(JDK) $(CPATH) `find m19 -name *.java`

run: m19/app/App.class
	$(JRE) $(CPATH) m19/app/App

m19/app/App.class:
	make all

clean:
	find . -name "*.class" -type f -delete

remake:
	make clean
	make