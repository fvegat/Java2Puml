clean:
	gradle clean

assemble: clean
	gradle assemble

run: assemble
	java -jar build/libs/Java2Puml.jar build/classes/main/
