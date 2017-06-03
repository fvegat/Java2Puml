package com.fvegat.java2puml.model.method_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

public class PrivateMethodObject extends MethodObject {
    @Override
    public String draw() {
        return ClassVisibilityTypes.PRIVATE + this.name + "(" + drawArguments() + "): " + returnType;
    }
}
