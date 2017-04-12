package com.fvegat.java2puml.model.class_object;

import com.fvegat.java2puml.constant.ClassTypes;
import com.fvegat.java2puml.model.DiagramObject;

import java.util.ArrayList;

public class ConcreteClassObject extends ClassObject implements DiagramObject {
    public ConcreteClassObject() {
        this.methods = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.fields = new ArrayList<>();
    }

    @Override
    public String draw() {
        return ClassTypes.CONCRETE + " " + this.name;
    }
}
