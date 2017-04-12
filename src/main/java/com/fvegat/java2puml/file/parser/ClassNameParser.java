package com.fvegat.java2puml.file.parser;

public class ClassNameParser {
    public static String convertFileNameToPackage(String fileName) {
        String packageName = fileName.replace("/", ".");

        return packageName;
    }

    public static String cleanClassName(String classNameWithPath) {
        String[] tempArray = classNameWithPath.split("/");
        return tempArray[tempArray.length -1];
    }
}
