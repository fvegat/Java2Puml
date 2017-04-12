package com.fvegat.java2puml;

import com.fvegat.java2puml.file.parser.JavaClassParser;
import com.fvegat.java2puml.file.writer.PumlWriter;

public class Main {
    public static void main(String[] args) {

        if (args.length == 2) {
            String projectPath = args[0];
            String output = args[1];

            try {
                JavaClassParser javaClassParser = new JavaClassParser(projectPath);
                PumlWriter pumlWriter = new PumlWriter(javaClassParser.parseJavaFiles(), output);
                pumlWriter.write();

            } catch(Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else
            System.out.println("Error: Invalid number of arguments");


    }
}
