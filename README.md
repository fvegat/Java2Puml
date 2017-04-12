# Java2Puml

## Automatic diagram generation using PlantUML syntax

### Usage:

```sh
java -jar Java2Puml.jar <path/to/classes/starting/in/com/> <output/diagram/file.puml>
```

For example, if your project has classes and packages like:

```
/home/fvegat/Projects/project/com/fvegat/classes/Main.java
```

You should execute Java2Puml like this creating the diagram file in /tmp:

```sh
java -jar Java2Puml.jar /home/fvegat/Projects/project/ /tmp/diagram.puml
```
