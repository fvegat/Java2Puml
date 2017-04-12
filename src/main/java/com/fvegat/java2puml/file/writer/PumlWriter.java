package com.fvegat.java2puml.file.writer;

import com.fvegat.java2puml.model.DiagramObject;
import com.fvegat.java2puml.model.class_object.ClassObject;
import com.fvegat.java2puml.model.field_object.ClassField;
import com.fvegat.java2puml.model.relation_object.ClassRelation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

public class PumlWriter {
    private List<DiagramObject> diagramObjects;
    private String outPutFilePath;

    public PumlWriter(List<DiagramObject> diagramObjects, String outPutFilePath) {
        this.diagramObjects = diagramObjects;
        this.outPutFilePath = outPutFilePath;
    }

    public int write() {
        int writeCount = diagramObjects.size();

        OutputStreamWriter pumlFileWriter = null;
        try {
            pumlFileWriter = new OutputStreamWriter(new FileOutputStream(outPutFilePath), Charset.forName("UTF-8").newEncoder());
            pumlFileWriter.append("@startuml\n");

            this.writeClasses(pumlFileWriter);
            this.writeRelations(pumlFileWriter);

            pumlFileWriter.append("@enduml\n");
        } catch (IOException ex) {
            System.out.println("ERROR writing classes");
        } finally {
            if (pumlFileWriter != null){
                try {
                    pumlFileWriter.close();
                } catch (IOException ex) {

                }
            }
        }

        return writeCount;
    }

    private void writeClasses(OutputStreamWriter pumlFileWriter) throws IOException {
        for (DiagramObject diagramObject: diagramObjects) {
            pumlFileWriter.append(diagramObject.draw());
            writeFields(diagramObject, pumlFileWriter);
            pumlFileWriter.append("\n");
        }
    }

    private void writeRelations(OutputStreamWriter pumlFileWriter) throws IOException {
        for (DiagramObject diagramObject : this.diagramObjects) {
            for (DiagramObject relation : ((ClassObject) diagramObject).getRelations()) {
                if (((ClassRelation) relation).isDrawable()){
                    pumlFileWriter.append(relation.draw() + " " + ((ClassObject) diagramObject).getName() + "\n");
                }
            }
        }
    }

    private void writeFields(DiagramObject diagramObject, OutputStreamWriter pumlFileWriter) throws IOException {
        pumlFileWriter.append(" {\n");
        for (DiagramObject classField: ((ClassObject)diagramObject).getFields()) {
            pumlFileWriter.append(((ClassField)classField).draw());
            pumlFileWriter.append("\n");
        }
        pumlFileWriter.append(" \n}");
    }
}
