package com.github.iceant.ueditor.spring.boot.starter.utils;

import com.github.iceant.ueditor.spring.boot.starter.beans.FileInfo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static String getSuffix(String fileName){
        if(fileName.lastIndexOf(".")==-1) return "";
        return fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
    }

    public static FileInfo getFileInfo(File file){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file.getName());
        fileInfo.setOriginalFileName(file.getName());
        fileInfo.setFilePath(file.getAbsolutePath());
        fileInfo.setContentType(MimeTypeUtil.getInstance().getMimetype(file));
        fileInfo.setFileSize(file.length());
        return fileInfo;
    }

    public static void listFiles(String startPath, FileFilter fileFilter, List<FileInfo> result) {
        File dir = new File(startPath);
        File[] files = (fileFilter==null)?dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory();
            }
        }):dir.listFiles(fileFilter);

        File[] paths = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if(files!=null) {
            for (File file : files) {
                result.add(getFileInfo(file));
            }
        }
        if(paths!=null) {
            for (File path : paths) {
                listFiles(path.getAbsolutePath(), fileFilter, result);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getSuffix("image.jpg"));
        System.out.println(getSuffix("imagejpg"));
        List<FileInfo> result = new ArrayList<>();
        listFiles("D:\\temp\\canvas", null, result);
        for(FileInfo fileInfo : result){
            System.out.println(fileInfo.getFilePath());
        }
    }
}
