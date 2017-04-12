package com.fvegat.java2puml.model.attribute_object;

import com.fvegat.java2puml.model.DiagramObject;

public abstract class ClassAttribute implements DiagramObject {
    protected String name;
    protected String type;
    protected String complexType;
    protected boolean isComplexType;
}
