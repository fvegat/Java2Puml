package com.fvegat.java2puml.model.class_object;

import com.fvegat.java2puml.constant.ClassTypes;

public class AbstractClassObject extends ClassObject {
    @Override
    public String draw() {
        return ClassTypes.ABSTRACT + " " + this.name;
    }
}
