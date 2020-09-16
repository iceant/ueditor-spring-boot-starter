package com.github.iceant.ueditor.spring.boot.starter.services;

import com.github.iceant.ueditor.spring.boot.starter.UEditorProperties;
import com.github.iceant.ueditor.spring.boot.starter.beans.FileInfo;
import com.github.iceant.ueditor.spring.boot.starter.utils.FileUtil;
import com.github.iceant.ueditor.spring.boot.starter.utils.IOUtils;
import com.github.iceant.ueditor.spring.boot.starter.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class StorageService {
    private final UEditorProperties properties;

    public StorageService(UEditorProperties properties) {
        this.properties = properties;
    }

    public void copyTo(String filePath, OutputStream outputStream) throws IOException {
        String rootPath = properties.getFileVault();
        Path targetFilePath = Paths.get(rootPath, filePath);
        if(!targetFilePath.toFile().exists()){
            outputStream.close();
            return;
        }
        IOUtils.copyUseStream(new FileInputStream(targetFilePath.toFile()), outputStream, 1024, true);
    }

    public FileInfo copyInfo(MultipartFile file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setOriginalFileName(file.getOriginalFilename());
        fileInfo.setFileSize(file.getSize());
        fileInfo.setContentType(file.getContentType());
        return fileInfo;
    }

    private String formatPath(String pathFormat, FileInfo fileInfo) {
        String fileName = fileInfo.getOriginalFileName();
        Date dateTime = Calendar.getInstance().getTime();
        String realPath = StringUtils.replace(pathFormat, "{filename}", fileName);
        String randLengthFlag = StringUtils.get(pathFormat, "{rand:", "}");
        String rand = null;

        if(randLengthFlag!=null && randLengthFlag.length()>0) {
            rand = "";
            int randLength = Integer.parseInt(randLengthFlag);
            Random random = new Random();
            for (int i = 0; i < randLength; i++) {
                rand += random.nextInt(10);
            }
        }

        realPath = StringUtils.replace(realPath, "{time}", String.valueOf(dateTime.getTime()));
        realPath = StringUtils.replace(realPath, "{yyyy}", String.format("%04d", dateTime.getYear()+1900));
        realPath = StringUtils.replace(realPath, "{mm}", String.format("%02d", dateTime.getMonth()+1));
        realPath = StringUtils.replace(realPath, "{dd}", String.format("%02d", dateTime.getDate()));
        realPath = StringUtils.replace(realPath, "{hh}", String.format("%02d", dateTime.getHours()));
        realPath = StringUtils.replace(realPath, "{ii}", String.format("%02d", dateTime.getMinutes()));
        realPath = StringUtils.replace(realPath, "{ss}", String.format("%02d", dateTime.getSeconds()));
        if(rand!=null) {
            realPath = StringUtils.replace(realPath, "{rand:" + randLengthFlag + "}", rand);
        }
        return realPath;
    }

    public FileInfo saveImage(MultipartFile file) {
        if(file.getSize()>properties.getImageMaxSize()){
            throw new FilesizeLimitExceededException(properties.getImageMaxSize(), file.getSize());
        }
        FileInfo fileInfo = copyInfo(file);
        String rootPath = properties.getFileVault();
        String imagePathFormat = properties.getImagePathFormat();
        fileInfo.setFilePath(formatPath(imagePathFormat, fileInfo)+ "." + FileUtil.getSuffix(fileInfo.getOriginalFileName()));
        try {
            Path toPath = Paths.get(rootPath, fileInfo.getFilePath());
            File parent = toPath.toFile().getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            file.transferTo(toPath);
            fileInfo.setFileName(toPath.toFile().getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileInfo;
    }

    public FileInfo saveVideo(MultipartFile file) {
        if(file.getSize()>properties.getVideoMaxSize()){
            throw new FilesizeLimitExceededException(properties.getVideoMaxSize(), file.getSize());
        }
        FileInfo fileInfo = copyInfo(file);
        String rootPath = properties.getFileVault();
        String pathFormat = properties.getVideoPathFormat();
        fileInfo.setFilePath(formatPath(pathFormat, fileInfo)+ "." + FileUtil.getSuffix(fileInfo.getOriginalFileName()));
        try {
            Path toPath = Paths.get(rootPath, fileInfo.getFilePath());
            File parent = toPath.toFile().getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            file.transferTo(toPath);
            fileInfo.setFileName(toPath.toFile().getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileInfo;
    }

    public FileInfo saveScrawl(String base64) {
        byte[] bytes = Base64.decodeBase64(base64);
        if(bytes.length>properties.getScrawlMaxSize()){
            throw new FilesizeLimitExceededException(properties.getScrawlMaxSize(), bytes.length);
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileSize((long) bytes.length);
        fileInfo.setOriginalFileName(properties.getScrawlFieldName()+".jpg");

        String rootPath = properties.getFileVault();
        String pathFormat = properties.getScrawlPathFormat();
        fileInfo.setFilePath(formatPath(pathFormat, fileInfo)+ "." + FileUtil.getSuffix(fileInfo.getOriginalFileName()));
        try {
            Path toPath = Paths.get(rootPath, fileInfo.getFilePath());
            File parent = toPath.toFile().getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            IOUtils.write(bytes, new FileOutputStream(toPath.toFile()), 1024, true);
            fileInfo.setFileName(toPath.toFile().getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileInfo;
    }

    public FileInfo saveFile(MultipartFile file) {
        if(file.getSize()>properties.getFileMaxSize()){
            throw new FilesizeLimitExceededException(properties.getFileMaxSize(), file.getSize());
        }
        FileInfo fileInfo = copyInfo(file);
        String rootPath = properties.getFileVault();
        String pathFormat = properties.getFilePathFormat();
        fileInfo.setFilePath(formatPath(pathFormat, fileInfo)+ "." + FileUtil.getSuffix(fileInfo.getOriginalFileName()));
        try {
            Path toPath = Paths.get(rootPath, fileInfo.getFilePath());
            File parent = toPath.toFile().getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            file.transferTo(toPath);
            fileInfo.setFileName(toPath.toFile().getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileInfo;
    }


    public String getRelativePath(String fullpath){
        if(fullpath==null || fullpath.length()<1) return fullpath;
        fullpath = fullpath.replace("\\", "/");
        String rootPath = properties.getFileVault();
        int pos = fullpath.indexOf(rootPath);
        if(pos!=-1){
            return fullpath.substring(pos+rootPath.length());
        }
        return fullpath;
    }

    public List<FileInfo> listImage(int start, int size) {
        String rootPath = properties.getFileVault();
        String filePath = properties.getImageManagerListPath();
        File fileDir = Paths.get(rootPath, filePath).toFile();
        List<FileInfo> result = new ArrayList<>();
        FileUtil.listFiles(fileDir.getAbsolutePath(), new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String suffix = FileUtil.getSuffix(pathname.getName());
                return properties.getImageManagerAllowFiles().contains(suffix);
            }
        }, result);
        int toIndex = start+size;
        if(toIndex>=result.size()){
            toIndex = result.size();
        }
        return result.subList(start, toIndex);
    }

    public List<FileInfo> listFile(int start, int size) {
        String rootPath = properties.getFileVault();
        String filePath = properties.getFileManagerListPath();
        File fileDir = Paths.get(rootPath, filePath).toFile();
        List<FileInfo> result = new ArrayList<>();
        FileUtil.listFiles(fileDir.getAbsolutePath(), new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String suffix = FileUtil.getSuffix(pathname.getName());
                return properties.getFileManagerAllowFiles().contains(suffix);
            }
        }, result);
        int toIndex = start+size;
        if(toIndex>=result.size()){
            toIndex = result.size();
        }
        return result.subList(start, toIndex);
    }
}
