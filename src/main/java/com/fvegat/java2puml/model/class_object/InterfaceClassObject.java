package com.fvegat.java2puml.model.class_object;

import com.fvegat.java2puml.constant.ClassTypes;
import com.fvegat.java2puml.model.DiagramObject;

public class InterfaceClassObject extends ClassObject implements DiagramObject {
    @Override
    public String draw() {
        return ClassTypes.INTERFACE + " " + this.name;
    }
}
