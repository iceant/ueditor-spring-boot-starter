package com.github.iceant.ueditor.spring.boot.starter.controller;

import com.github.iceant.ueditor.spring.boot.starter.UEditorProperties;
import com.github.iceant.ueditor.spring.boot.starter.beans.FileInfo;
import com.github.iceant.ueditor.spring.boot.starter.beans.UEditorListResponse;
import com.github.iceant.ueditor.spring.boot.starter.beans.UEditorResponse;
import com.github.iceant.ueditor.spring.boot.starter.services.StorageService;
import com.github.iceant.ueditor.spring.boot.starter.utils.DebugUtil;
import com.github.iceant.ueditor.spring.boot.starter.utils.MimeTypeUtil;
import com.github.iceant.ueditor.spring.boot.starter.utils.RequestUtil;
import com.github.iceant.ueditor.spring.boot.starter.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(path = {"/ueditor"})
@RestController
public class UEditorController {

    final private UEditorProperties properties;
    final private StorageService storageService;

    public UEditorController(UEditorProperties properties, StorageService storageService) {
        this.properties = properties;
        this.storageService = storageService;
    }

    @RequestMapping(path = {"", "/**"})
    public void staticResource(HttpServletRequest request, HttpServletResponse response){
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        String relativePath = StringUtils.removePrefix(requestUri, contextPath);
        response.setContentType(MimeTypeUtil.getInstance().getMimetype(relativePath));
        try {
            storageService.copyTo(relativePath, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(path = {"/config"})
    public Object config(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        List<String> arrayFields = Arrays.asList("imageAllowFiles", "catcherLocalDomain", "catcherAllowFiles", "videoAllowFiles", "fileAllowFiles", "imageManagerAllowFiles", "fileManagerAllowFiles");
        for (Field field : properties.getClass().getDeclaredFields()) {
            Object value = null;
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                value = field.get(properties);
                if (arrayFields.contains(fieldName)) {
                    String strValue = (String) value;
                    map.put(fieldName, strValue.split(","));
                } else {
                    map.put(fieldName, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @RequestMapping(path = {"/uploadimage"})
    public Object uploadimage(MultipartHttpServletRequest request){
        MultipartFile file = request.getFile(properties.getImageFieldName());
        FileInfo fileInfo = storageService.saveImage(file);
        String contentPath = request.getContextPath()+"/ueditor/getimage?path="+fileInfo.getFilePath();
        return new UEditorResponse()
                .setState(UEditorResponse.STATE_SUCCESS)
                .setUrl(contentPath)
                .setOriginal(fileInfo.getOriginalFileName())
                .setTitle(fileInfo.getFileName());
    }

    @RequestMapping(path = {"/getimage", "/getvideo", "/getscrawl", "/getfile"})
    public void getFile(HttpServletRequest request, HttpServletResponse response){
        String path = request.getParameter("path");
        String contextPath = request.getContextPath();
        String filePath = StringUtils.replace(path, contextPath, "");
        String contentType= MimeTypeUtil.getInstance().getMimetype(filePath);
        response.setContentType(contentType);
        try {
            storageService.copyTo(filePath, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(path = {"/uploadvideo"})
    public Object uploadvideo(MultipartHttpServletRequest request){
        MultipartFile file = request.getFile(properties.getVideoFieldName());
        FileInfo fileInfo = storageService.saveVideo(file);
        String contentPath = request.getContextPath()+"/ueditor/getvideo?path="+fileInfo.getFilePath();
        return new UEditorResponse()
                .setState(UEditorResponse.STATE_SUCCESS)
                .setUrl(contentPath)
                .setOriginal(fileInfo.getOriginalFileName())
                .setTitle(fileInfo.getFileName());
    }

    @RequestMapping(path = {"/uploadscrawl"})
    public Object uploadscrawl(HttpServletRequest request){
        String base64encoded = request.getParameter(properties.getScrawlFieldName());
        FileInfo fileInfo = storageService.saveScrawl(base64encoded);
        String contentPath = request.getContextPath()+"/ueditor/getscrawl?path="+fileInfo.getFilePath();
        return new UEditorResponse()
                .setState(UEditorResponse.STATE_SUCCESS)
                .setUrl(contentPath)
                .setOriginal(fileInfo.getOriginalFileName())
                .setTitle(fileInfo.getFileName());
    }

    @RequestMapping(path = {"/uploadfile"})
    public Object uploadfile(MultipartHttpServletRequest request){
        MultipartFile file = request.getFile(properties.getFileFieldName());
        FileInfo fileInfo = storageService.saveFile(file);
        String contentPath = request.getContextPath()+"/ueditor/getfile?path="+fileInfo.getFilePath();
        return new UEditorResponse()
                .setState(UEditorResponse.STATE_SUCCESS)
                .setUrl(contentPath)
                .setOriginal(fileInfo.getOriginalFileName())
                .setTitle(fileInfo.getFileName());
    }


    @RequestMapping(path = {"/listimage"})
    public Object listimage(HttpServletRequest request){
        int imageManagerListSize = properties.getImageManagerListSize();
        int start = RequestUtil.paramInt("start", 0);
        int size = RequestUtil.paramInt("size", imageManagerListSize);
        List<FileInfo> files = storageService.listImage(start, size);

        UEditorListResponse response =  new UEditorListResponse()
                .setState("SUCCESS")
                .setStart(start)
                ;

        for(FileInfo fileInfo : files){
            response.addItemURL(storageService.getRelativePath(fileInfo.getFilePath()));
        }
        return response;
    }

    @RequestMapping(path = {"/listfile"})
    public Object listfile(HttpServletRequest request){
        int fileManagerListSize = properties.getFileManagerListSize();
        int start = RequestUtil.paramInt("start", 0);
        int size = RequestUtil.paramInt("size", fileManagerListSize);
        List<FileInfo> files = storageService.listFile(start, size);

        UEditorListResponse response =  new UEditorListResponse()
                .setState("SUCCESS")
                .setStart(start)
                ;

        for(FileInfo fileInfo : files){
            response.addItemURL(storageService.getRelativePath(fileInfo.getFilePath()));
        }
        return response;
    }

    @RequestMapping(path = {"/controller"})
    public Object controller(HttpServletRequest request) {
        String action = request.getParameter("action");
        if(action==null||action.length()<1){
            action = "config";
        }

        if("config".equalsIgnoreCase(action)){
            return config(request);
        }else if("listimage".equalsIgnoreCase(action)){
            return listimage(request);
        }else if("listfile".equalsIgnoreCase(action)){
            return listfile(request);
        }else if("uploadscrawl".equalsIgnoreCase(action)){
            return uploadscrawl(request);
        }

        if(request instanceof MultipartHttpServletRequest) {
            if ("uploadimage".equalsIgnoreCase(action)) {
                return uploadimage((MultipartHttpServletRequest) request);
            }else if("uploadvideo".equalsIgnoreCase(action)){
                return uploadvideo((MultipartHttpServletRequest) request);
            }else if("uploadfile".equalsIgnoreCase(action)){
                return uploadfile((MultipartHttpServletRequest) request);
            }
        }

        return new UEditorResponse().setState("UNKNOW ACTION");
    }
}
