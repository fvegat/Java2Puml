package com.fvegat.java2puml.model.field_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

public class PublicClassField extends ClassField {
    @Override
    public String draw() {
        return ClassVisibilityTypes.PUBLIC + this.name + ": " + this.type;
    }
}
