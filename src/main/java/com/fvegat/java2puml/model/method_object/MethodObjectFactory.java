package com.fvegat.java2puml.model.method_object;

import org.objectweb.asm.Opcodes;

public class MethodObjectFactory {
    public static MethodObject getInstance(int access) {
        if (access == Opcodes.ACC_PRIVATE)
            return new PrivateMethodObject();
        else if (access == Opcodes.ACC_PROTECTED)
            return new ProtectedMethodObject();
        else
            return new PublicMethodObject();
    }
}
