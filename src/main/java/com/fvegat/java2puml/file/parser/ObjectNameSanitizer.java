package com.fvegat.java2puml.file.parser;

public class ObjectNameSanitizer {
    public static String convertFileNameToPackage(String fileName) {
        String packageName = fileName.replace("/", ".");

        return packageName;
    }

    public static String cleanClassName(String classNameWithPath) {
        String[] tempArray = classNameWithPath.split("/");
        return tempArray[tempArray.length -1];
    }

    public static String cleanFieldName(String fieldName, String signature) {
        if ("Z".equals(fieldName))
            return "boolean";

        else if(signature == null) {
            String[] splittedAttributeName = fieldName.split("/");
            return splittedAttributeName[splittedAttributeName.length -1].replace(";", "");
        }
        else {
            String[] splittedAttributeName = signature.split("/");
            return splittedAttributeName[splittedAttributeName.length -1].replace(";", "").replace("<", "").replace(">", "");
        }

    }
}
