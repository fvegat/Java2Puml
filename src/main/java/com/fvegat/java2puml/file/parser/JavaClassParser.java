package com.fvegat.java2puml.file.parser;

import com.fvegat.java2puml.file.loader.JavaClassLoader;
import com.fvegat.java2puml.model.DiagramObject;
import com.fvegat.java2puml.model.class_object.ClassObject;
import com.fvegat.java2puml.model.class_object.ClassObjectFactory;
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

    private List<DiagramObject> parseMethods() {
        return new ArrayList<>();
    }

    private List<DiagramObject> parseRelations() {
        return new ArrayList<>();
    }

    private List<DiagramObject> parseFields() {
        return new ArrayList<>();
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        ClassObject classObject = ClassObjectFactory.getInstance(access);
        classObject.setName(ClassNameParser.cleanClassName(name));
        List<DiagramObject> relations = new ArrayList<>();

        ClassRelation inheritanceRelation = new InheritanceRelation();
        inheritanceRelation.setRelation(ClassNameParser.cleanClassName(superName));
        inheritanceRelation.setDrawable(true);
        relations.add(inheritanceRelation);

        for (String interFace: interfaces) {
            ClassRelation implementationRelation = new ImplementationRelation();
            implementationRelation.setRelation(ClassNameParser.cleanClassName(interFace));
            implementationRelation.setDrawable(true);
            relations.add(implementationRelation);
        }
        classObject.setRelations(relations);
        this.parsedClasses.add(classObject);
    }

}