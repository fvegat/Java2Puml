package com.fvegat.java2puml.model.field_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

public class ProtectedClassField extends ClassField {
    @Override
    public String draw() {
        return ClassVisibilityTypes.PROTECTED + this.name + ": " + this.type;
    }
}
