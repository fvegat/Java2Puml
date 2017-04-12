package com.fvegat.java2puml.file.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fvegat on 18-12-16.
 */
public class JavaClassLoader {
    private static final String CLASS_FILE_EXTENSION = ".class";

    public List<File> getClassFiles(String rootSourceDirectory) {
        List<File> filePaths = new ArrayList<>();
        this.findClasses(rootSourceDirectory, filePaths);

        return filePaths;
    }

    private void findClasses(String rootSourceDirectory, List<File> filePaths) {
        File root = new File(rootSourceDirectory);
        File[] fileList = root.listFiles();
        if (fileList != null){
            List<File> files = new ArrayList<>(Arrays.asList(fileList));
            for (File file : files) {
                if (file.isDirectory()){
                    findClasses(file.getAbsolutePath(), filePaths);
                } else if (file.getAbsolutePath().endsWith(CLASS_FILE_EXTENSION)){
                    filePaths.add(new File(file.getAbsolutePath()));
                }
            }
        }
    }
}
