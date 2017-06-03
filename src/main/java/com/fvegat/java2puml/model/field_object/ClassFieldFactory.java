package com.fvegat.java2puml.model.field_object;

import org.objectweb.asm.Opcodes;

public class ClassFieldFactory {
    public static ClassField getInstance(int access) {
        if (access == Opcodes.ACC_PRIVATE){
            return new PrivateClassField();
        } else if (access == Opcodes.ACC_PROTECTED){
            return new ProtectedClassField();
        } else{
            return new PublicClassField();
        }
    }
}
