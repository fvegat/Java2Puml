package com.fvegat.java2puml.file.parser;

import com.fvegat.java2puml.file.loader.JavaClassLoader;
import com.fvegat.java2puml.model.DiagramObject;
import com.fvegat.java2puml.model.class_object.ClassObject;
import com.fvegat.java2puml.model.class_object.ClassObjectFactory;
import com.fvegat.java2puml.model.field_object.ClassField;
import com.fvegat.java2puml.model.field_object.ClassFieldFactory;
import com.fvegat.java2puml.model.method_object.MethodObject;
import com.fvegat.java2puml.model.method_object.MethodObjectFactory;
import com.fvegat.java2puml.model.relation_object.*;
import org.objectweb.asm.*;

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

        postProcessRelations();
        return this.parsedClasses;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.currentClassObject = ClassObjectFactory.getInstance(access);
        this.currentClassObject.setName(ObjectNameSanitizer.cleanClassName(name));
        List<DiagramObject> relations = new ArrayList<>();

        ClassRelation inheritanceRelation = new InheritanceRelation();
        inheritanceRelation.setRelation(ObjectNameSanitizer.cleanClassName(superName));
        checkDrawableRelationObjects(inheritanceRelation);
        relations.add(inheritanceRelation);

        for (String interFace: interfaces) {
            ClassRelation implementationRelation = new ImplementationRelation();
            implementationRelation.setRelation(ObjectNameSanitizer.cleanClassName(interFace));
            checkDrawableRelationObjects(implementationRelation);
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

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodObject methodObject = MethodObjectFactory.getInstance(access);
        methodObject.setName(name);
        methodObject.setReturnType(ObjectNameSanitizer.parseMethodReturnType(desc));
        methodObject.setArguments(ObjectNameSanitizer.parseMethodArguments(desc));
        checkDrawableMethods(methodObject);
        currentClassObject.getMethods().add(methodObject);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    private void postProcessRelations() {
        for (DiagramObject classObject: parsedClasses) {
            for (DiagramObject methodObject: ((ClassObject)classObject).getMethods()) {
                if (((MethodObject)methodObject).getName().equals(((ClassObject)classObject).getName())) {
                    CompositionRelation compositionRelation = new CompositionRelation();
                    compositionRelation.setRelation(((MethodObject)methodObject).getName());
                    compositionRelation.setDrawable(true);
                    ((ClassObject)classObject).getRelations().add(compositionRelation);
                }

                for (String argument: ((MethodObject)methodObject).getArguments()) {
                    if (argument.equals(((ClassObject)classObject).getName())) {
                        DependencyRelation dependencyRelation = new DependencyRelation();
                        dependencyRelation.setRelation(argument);
                        dependencyRelation.setDrawable(true);
                        ((ClassObject)classObject).getRelations().add(dependencyRelation);
                    }
                }
            }
        }
    }

    private void checkDrawableRelationObjects(ClassRelation classRelation) {
        for (DiagramObject diagramObject: this.parsedClasses) {
            if (classRelation.getRelation().equals(((ClassObject)diagramObject).getName()))
                classRelation.setDrawable(true);
        }
    }

    private void checkDrawableMethods(MethodObject methodObject) {
        if (methodObject.getName().contains("<init>") || methodObject.getName().contains("<clinit>"))
            methodObject.setDrawable(false);
        else
            methodObject.setDrawable(true);
    }
}