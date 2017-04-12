package com.fvegat.java2puml.model.field_object;

import com.fvegat.java2puml.file.parser.ObjectNameSanitizer;
import com.fvegat.java2puml.model.DiagramObject;

public abstract class ClassField implements DiagramObject {
    protected String name;
    protected String type;
    protected String complexType;
    protected boolean isComplexType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type, String complexType) {
        if (complexType == null)
            this.type = ObjectNameSanitizer.cleanFieldName(type, complexType);
        else {
            String tempType = ObjectNameSanitizer.cleanFieldName(type, null);
            String tempComplexType = ObjectNameSanitizer.cleanFieldName(type, complexType);
            this.type = tempType + "<" + tempComplexType + ">";
        }
    }

    public String getComplexType() {
        return complexType;
    }

    public void setComplexType(String complexType) {
        this.complexType = complexType;
    }

    public boolean isComplexType() {
        return isComplexType;
    }

    public void setComplexType(boolean complexType) {
        isComplexType = complexType;
    }
}
