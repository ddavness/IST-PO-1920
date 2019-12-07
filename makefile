JAR = jar
JDK = javac
JRE = java
CPATH = -cp po-uilib.jar:.

all: m19/app/App.java
	$(JDK) $(CPATH) `find m19 -name *.java`

run: m19/app/App.class
	$(JRE) $(CPATH) m19/app/App

m19/app/App.class: all

clean:
	find . -name "*.class" -type f -delete
	rm -f 1 requisicao user works cumpridor faltoso notificacao
	rm -rf tests/*.diff tests/*.outhyp

remake:
	make clean
	make

pkg: clean
	$(JAR) cvf m19.jar -C . m19

test: clean all
	./runtests.sh