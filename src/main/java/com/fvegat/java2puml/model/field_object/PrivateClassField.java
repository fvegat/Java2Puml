package com.fvegat.java2puml.model.field_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

public class PrivateClassField extends ClassField {
    @Override
    public String draw() {
        return ClassVisibilityTypes.PRIVATE + this.name + ": " + this.type;
    }
}
