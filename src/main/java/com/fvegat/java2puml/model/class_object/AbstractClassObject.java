package com.fvegat.java2puml.model.class_object;

import com.fvegat.java2puml.constant.ClassTypes;

import java.util.ArrayList;

public class AbstractClassObject extends ClassObject {

    public AbstractClassObject() {
        this.methods = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.fields = new ArrayList<>();
    }

    @Override
    public String draw() {
        return ClassTypes.ABSTRACT + " " + this.name;
    }
}
