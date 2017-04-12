package com.fvegat.java2puml.model.attribute_object;

import com.fvegat.java2puml.constant.ClassVisibilityTypes;

/**
 * Created by fvegat on 18-12-16.
 */
public class ProtectedClassAttribute extends ClassAttribute {
    @Override
    public String draw() {
        return ClassVisibilityTypes.PROTECTED + this.name;
    }
}
