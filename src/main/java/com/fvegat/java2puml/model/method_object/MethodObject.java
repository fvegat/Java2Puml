package com.fvegat.java2puml.model.method_object;

import com.fvegat.java2puml.model.DiagramObject;

import java.util.List;

public abstract class MethodObject implements DiagramObject {
    protected String name;
    protected String returnType;
    protected List<String> arguments;
    protected boolean drawable;

    protected String drawArguments() {
        String argumentsToDraw = "";
        int trimIndex = 0;
        for (String argument: arguments) {
            argumentsToDraw += argument + ", ";
            trimIndex = 2;
        }

        return argumentsToDraw.substring(0, argumentsToDraw.length()-trimIndex);
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

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
}
