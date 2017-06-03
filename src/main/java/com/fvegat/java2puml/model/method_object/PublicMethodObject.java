package com.fvegat.java2puml.model.method_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

public class PublicMethodObject extends MethodObject {
    @Override
    public String draw() {
        return ClassVisibilityTypes.PUBLIC + this.name + "(" + drawArguments() + "): " + returnType;
    }
}
