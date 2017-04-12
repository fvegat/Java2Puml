/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvegat.java2puml.model.relation_object;

import com.fvegat.java2puml.constant.ClassRelationTypes;

public class AggregationRelation extends ClassRelation {

    @Override
    public String draw() {
        return ClassRelationTypes.AGGREGATION + " " + this.relation;
    }

}
