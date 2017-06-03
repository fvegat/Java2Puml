package com.fvegat.java2puml.model.method_object;

import com.fvegat.java2puml.file.parser.ObjectNameSanitizer;
import com.fvegat.java2puml.model.DiagramObject;
import com.fvegat.java2puml.model.field_object.ClassField;

import java.util.ArrayList;
import java.util.List;

public abstract class MethodObject implements DiagramObject {
    protected String name;
    protected String returnType;
    protected List<ClassField> arguments;
    protected boolean drawable;

    public MethodObject() {
        this.arguments = new ArrayList<>();
    }

    protected String drawArguments() {
        String argumentsToDraw = " ";

        for (ClassField classField: arguments)
            argumentsToDraw.concat(classField.getName()
                    + ": "
                    + ObjectNameSanitizer.cleanFieldName(classField.getName(), null)
                    + ", ");

        return argumentsToDraw.substring(0, argumentsToDraw.length() -1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<ClassField> getArguments() {
        return arguments;
    }

    public void setArguments(List<ClassField> arguments) {
        this.arguments = arguments;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
}
