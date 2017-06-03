package com.fvegat.java2puml.file.parser;

import com.fvegat.java2puml.model.field_object.ClassField;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectNameSanitizer {
    public static final String BOOLEAN = "boolean";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
    public static final String INT = "int";
    public static final String VOID = "void";

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
            return BOOLEAN;
        else if ("I".equals(fieldName))
            return INT;
        else if ("D".equals(fieldName))
            return DOUBLE;
        else if ("F".equals(fieldName))
            return FLOAT;
        else if ("V".equals(fieldName))
            return VOID;
        else if(signature == null) {
            String[] splittedAttributeName = fieldName.split("/");
            return splittedAttributeName[splittedAttributeName.length -1].replace(";", "");
        }
        else {
            String[] splittedAttributeName = signature.split("/");
            return splittedAttributeName[splittedAttributeName.length -1].replace(";", "").replace("<", "").replace(">", "");
        }

    }

    public static String parseMethodReturnType(String methodDescription) {
        Pattern pattern = Pattern.compile("\\(.*\\)(.+)");
        Matcher matcher = pattern.matcher(methodDescription);

        if (matcher.find())
            return cleanFieldName(matcher.group(1), null);
        else
            return "";
    }

    public static List<String> parseMethodArguments(String methodDescription) {
        Pattern pattern = Pattern.compile("(\\[*L[^;]+;|\\[[ZBCSIFDJ]|[ZBCSIFDJ])");
        Matcher matcher = pattern.matcher(methodDescription);

        List<String> matches = new ArrayList<>();
        while(matcher.find()) {
            matches.add(cleanFieldName(matcher.group(), null));
        }

        return matches;
    }
}
