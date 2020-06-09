package com.github.iceant.ueditor.spring.boot.starter.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes instanceof ServletRequestAttributes){
            return ((ServletRequestAttributes)requestAttributes).getRequest();
        }
        return null;
    }

    public static Integer paramInt(String name, Integer defaultValue){
        String value = getRequest().getParameter(name);
        if(value==null||value.length()<1){
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        }catch (Exception err){
            return defaultValue;
        }
    }

    public static Integer paramInt(String name){
        return paramInt(name, null);
    }

    public static String paramString(String name, String defaultValue){
        String value = getRequest().getParameter(name);
        if(value==null){
            return defaultValue;
        }
        return value;
    }

    public static String paramString(String name){
        return paramString(name, null);
    }

    public static Long paramLong(String name, Long defaultValue){
        String value = getRequest().getParameter(name);
        if(value==null||value.length()<1){
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        }catch (Exception err){
            return defaultValue;
        }
    }

    public static Long paramLong(String name){
        return paramLong(name, null);
    }
}
