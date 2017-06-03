package com.fvegat.java2puml.model.method_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

public class ProtectedMethodObject extends MethodObject {

    @Override
    public String draw() {
        return ClassVisibilityTypes.PROTECTED + this.name + "(" + drawArguments() + "): " + returnType;
    }
}
