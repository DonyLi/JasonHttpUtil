package com.jason.jasonhttputil;

import android.widget.Button;

import java.io.File;

public class FileBody {
    private String key;
    private String fileName;
    private String contentType;
    private File file;


    public FileBody(String fileName, String contentType, File file) {
        setFile(file);
        setFileName(fileName);
        setContentType(contentType);
    }

    public FileBody(String fileName, String contentType, String path) {
        this(fileName, contentType, new File(path));
    }

    public FileBody(String path) {
        this(null, null, new File(path));
    }

    public FileBody(String contentType, String path) {
        this(null, contentType, new File(path));
    }

    public FileBody(File file) {
        this(null, null, file);

    }

    public FileBody(String contentType, File file) {
        this(null, contentType, file);
    }


    public void setKey(String key) {
        this.key = key == null ? "file" : key;


    }

    public void setFileName(String fileName) {
        if (fileName != null) {
            this.fileName = fileName;
        }
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? "application/octet-stream" : contentType;
    }

    public void setFile(File file) {
        if (file == null) {
            throw new IllegalStateException("文件不能为空");
        }
        if (fileName == null) {
            this.fileName = file.getName();
        }
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public File getFile() {
        return file;
    }
}
