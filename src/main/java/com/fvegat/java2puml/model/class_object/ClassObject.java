package com.fvegat.java2puml.model.class_object;

import com.fvegat.java2puml.model.DiagramObject;
import java.util.List;

public abstract class ClassObject implements DiagramObject {
    protected String name;
    protected List<DiagramObject> relations;
    protected List<DiagramObject> fields;
    protected List<DiagramObject> methods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiagramObject> getRelations() {
        return relations;
    }

    public void setRelations(List<DiagramObject> relations) {
        this.relations = relations;
    }

    public List<DiagramObject> getFields() {
        return fields;
    }

    public void setFields(List<DiagramObject> fields) {
        this.fields = fields;
    }

    public List<DiagramObject> getMethods() {
        return methods;
    }

    public void setMethods(List<DiagramObject> methods) {
        this.methods = methods;
    }
}
