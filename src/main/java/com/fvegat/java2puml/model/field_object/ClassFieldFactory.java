package com.fvegat.java2puml.model.field_object;

import org.objectweb.asm.Opcodes;

public class ClassFieldFactory {
    public static ClassField getInstance(int access) {
        if ((access & Opcodes.ACC_PRIVATE) != 0){
            return new PublicClassField();
        } else if ((access & Opcodes.ACC_PROTECTED) != 0){
            return new ProtectedClassField();
        } else{
            return new PublicClassField();
        }
    }
}
