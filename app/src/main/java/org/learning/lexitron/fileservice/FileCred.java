package org.learning.lexitron.fileservice;

import android.os.Environment;

public class FileCred {

    private static int file_number;
    private String fileName;
    private String baseDir;
    private String pathDir;

    public FileCred(String fn){
        fileName = fn+(++file_number)+".jpg";
        baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        pathDir = baseDir + "/Android/data/org.learning.lexitron/";
    }

    public static int getFile_number() {
        return file_number;
    }

    public static void setFile_number(int file_number) {
        FileCred.file_number = file_number;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getPathDir() {
        return pathDir;
    }

    public void setPathDir(String pathDir) {
        this.pathDir = pathDir;
    }
}
