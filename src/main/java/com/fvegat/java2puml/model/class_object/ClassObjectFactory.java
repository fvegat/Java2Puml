package com.fvegat.java2puml.model.class_object;

import org.objectweb.asm.Opcodes;

public class ClassObjectFactory {
    public static ClassObject getInstance(int access) {
        if ((access & Opcodes.ACC_INTERFACE) != 0){
            return new InterfaceClassObject();
        }else if ((access & Opcodes.ACC_ABSTRACT) != 0){
            return new AbstractClassObject();
        }else{
            return new ConcreteClassObject();
        }
    }
}
