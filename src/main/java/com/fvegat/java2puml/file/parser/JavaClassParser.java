package com.fvegat.java2puml.file.parser;

import com.fvegat.java2puml.file.loader.JavaClassLoader;
import com.fvegat.java2puml.model.DiagramObject;
import com.fvegat.java2puml.model.class_object.ClassObject;
import com.fvegat.java2puml.model.class_object.ClassObjectFactory;
import com.fvegat.java2puml.model.field_object.ClassField;
import com.fvegat.java2puml.model.field_object.ClassFieldFactory;
import com.fvegat.java2puml.model.relation_object.ClassRelation;
import com.fvegat.java2puml.model.relation_object.ImplementationRelation;
import com.fvegat.java2puml.model.relation_object.InheritanceRelation;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class JavaClassParser extends ClassVisitor {
    private JavaClassLoader javaClassLoader;
    private List<DiagramObject> parsedClasses;
    private String projectPath;
    private ClassObject currentClassObject;

    public JavaClassParser(String projectPath) {
        super(Opcodes.ASM5);
        this.javaClassLoader = new JavaClassLoader();
        this.parsedClasses = new ArrayList<>();
        this.projectPath = projectPath;
    }

    public List<DiagramObject> parseJavaFiles() throws Exception{
        List<File> classFiles = this.javaClassLoader.getClassFiles(this.projectPath);

        for (File classFile: classFiles) {
            ClassReader classReader = new ClassReader(new FileInputStream(classFile));
            classReader.accept(this, 0);
        }

        return this.parsedClasses;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.currentClassObject = ClassObjectFactory.getInstance(access);
        this.currentClassObject.setName(ObjectNameSanitizer.cleanClassName(name));
        List<DiagramObject> relations = new ArrayList<>();

        ClassRelation inheritanceRelation = new InheritanceRelation();
        inheritanceRelation.setRelation(ObjectNameSanitizer.cleanClassName(superName));
        checkDrawableObjects(inheritanceRelation);
        relations.add(inheritanceRelation);

        for (String interFace: interfaces) {
            ClassRelation implementationRelation = new ImplementationRelation();
            implementationRelation.setRelation(ObjectNameSanitizer.cleanClassName(interFace));
            checkDrawableObjects(implementationRelation);
            relations.add(implementationRelation);
        }
        this.currentClassObject.setRelations(relations);
        this.parsedClasses.add(this.currentClassObject);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        ClassField classField = ClassFieldFactory.getInstance(access);
        classField.setName(ObjectNameSanitizer.cleanClassName(name));
        classField.setType(desc, signature);

        currentClassObject.getFields().add(classField);
        return super.visitField(access, name, desc, signature, value);
    }

    private void checkDrawableObjects(ClassRelation classRelation) {
        for (DiagramObject diagramObject: this.parsedClasses) {
            if (classRelation.getRelation().equals(((ClassObject)diagramObject).getName()))
                classRelation.setDrawable(true);
        }
    }
}