package com.github.iceant.ueditor.spring.boot.starter.utils;

public class StringUtils {

    public static String replace(String string, String tag, String content){
        if(tag==null || tag.length()<1) return string;
        int start = 0;
        int end = string.indexOf(tag);
        int tagLen = tag.length();

        while(end!=-1){
            string = string.substring(start, end)+content+string.substring(end+tagLen);
            start = end+tagLen;
            end = string.indexOf(tag, start);
        }
        return string;
    }

    public static String get(String content, String start, String end){
        int beginPos = content.indexOf(start);
        int endPos = content.indexOf(end, beginPos+start.length());
        if(beginPos==-1 || endPos==-1) return null;
        return content.substring(beginPos+start.length(), endPos);
    }

    public static void main(String[] args){
        String path = "/ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";
        String result = replace(path , "{yyyy}", "2020");
        result = replace(result , "{mm}", "15");
        result = replace(result , "{yy}", "22");
        System.out.println(result);
        System.out.println(get("/ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}" , "{rand:", "}"));
    }

    public static String removePrefix(String string, String prefix) {
        if(string==null || string.length()<1) return string;
        if(prefix==null || prefix.length()<1) return string;
        int pos = string.indexOf(prefix);
        if(pos!=-1) return string.substring(pos+prefix.length());
        return string;
    }
}
