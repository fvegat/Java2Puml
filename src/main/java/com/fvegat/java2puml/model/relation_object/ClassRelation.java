/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvegat.java2puml.model.relation_object;

import com.fvegat.java2puml.model.DiagramObject;

public abstract class ClassRelation implements DiagramObject {
    protected String relation;
    protected boolean drawable;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

}
