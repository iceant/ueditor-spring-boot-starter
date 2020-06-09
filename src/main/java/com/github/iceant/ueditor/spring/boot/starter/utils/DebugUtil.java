package com.github.iceant.ueditor.spring.boot.starter.utils;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class DebugUtil {

    public static void printRequest(HttpServletRequest request) {
        System.out.println("==================================================");
        System.out.println("request class:"+request.getClass());
        System.out.println("AuthType:" + request.getAuthType());
        System.out.println("RequestURI:" + request.getRequestURI());
        System.out.println("ContextPath:" + request.getContextPath());
        System.out.println("Method:" + request.getMethod());
        System.out.println("PathInfo:" + request.getPathInfo());
        System.out.println("PathTranslated:" + request.getPathTranslated());
        System.out.println("QueryString:" + request.getQueryString());
        System.out.println("RemoteUser:" + request.getRemoteUser());
        System.out.println("RequestedSessionId:" + request.getRequestedSessionId());
        System.out.println("ServletPath:" + request.getServletPath());
        System.out.println("CharacterEncoding:" + request.getCharacterEncoding());
        System.out.println("ContentType:" + request.getContentType());
        System.out.println("LocalAddr:" + request.getLocalAddr());
        System.out.println("LocalName:" + request.getLocalName());
        System.out.println("Protocol:" + request.getProtocol());
        System.out.println("RemoteAddr:" + request.getRemoteAddr());
        System.out.println("RemoteHost:" + request.getRemoteHost());
        System.out.println("Schema:" + request.getScheme());
        System.out.println("ServerName:" + request.getServerName());
        System.out.println("Cookies[]:" + Arrays.toString(request.getCookies()));
        System.out.println("HeaderNames:" + request.getHeaderNames());
        Enumeration<String> headerNames = request.getHeaderNames();
        for (; headerNames.hasMoreElements(); ) {
            String headerName = headerNames.nextElement();
            System.out.println("\t HEADER[" + headerName + "] = " + request.getHeader(headerName));
        }
        System.out.println("RequestURL:" + request.getRequestURL());
        System.out.println("Session:" + request.getSession());
        System.out.println("UserPrincipal:" + request.getUserPrincipal());
        System.out.println("AttributeNames:" + request.getAttributeNames());
        System.out.println("ContentLength:" + request.getContentLength());
        System.out.println("DispatcherType:" + request.getDispatcherType());
        System.out.println("Locale:" + request.getLocale());
        System.out.println("ParameterMap:" + request.getParameterMap());
        System.out.println("ServletContext.getRealPath('/'): " + request.getServletContext().getRealPath("/"));
        System.out.println("================");
        System.out.println("== PARAMETERS ==");
        Map<String, String[]> parmas = request.getParameterMap();
        for (String name : parmas.keySet()) {
            String[] values = parmas.get(name);
            System.out.println(name + " = " + Arrays.toString(values));
        }
        System.out.println("================");
        System.out.println("== SESSION    ==");
        Enumeration<String> sessionKeys = request.getSession().getAttributeNames();
        for (; sessionKeys.hasMoreElements(); ) {
            String key = sessionKeys.nextElement();
            Object value = request.getSession().getAttribute(key);
            System.out.println("\tKey:" + key + ", Value: " + value);
        }

        System.out.println("================");
        System.out.println("== ATTRIBUTES ==");
        Enumeration<String> names = request.getAttributeNames();
        for (; names.hasMoreElements(); ) {
            String name = names.nextElement();
            Object value = request.getAttribute(name);
            System.out.println("\tKey:" + name + ", Value: " + value);
        }
    }

    public static void debug(Logger log, HttpServletRequest request) {
        log.debug("==================================================");
        log.debug("AuthType:" + request.getAuthType());
        log.debug("RequestURI:" + request.getRequestURI());
        log.debug("ContextPath:" + request.getContextPath());
        log.debug("Method:" + request.getMethod());
        log.debug("PathInfo:" + request.getPathInfo());
        log.debug("PathTranslated:" + request.getPathTranslated());
        log.debug("QueryString:" + request.getQueryString());
        log.debug("RemoteUser:" + request.getRemoteUser());
        log.debug("RequestedSessionId:" + request.getRequestedSessionId());
        log.debug("ServletPath:" + request.getServletPath());
        log.debug("CharacterEncoding:" + request.getCharacterEncoding());
        log.debug("ContentType:" + request.getContentType());
        log.debug("LocalAddr:" + request.getLocalAddr());
        log.debug("LocalName:" + request.getLocalName());
        log.debug("Protocol:" + request.getProtocol());
        log.debug("RemoteAddr:" + request.getRemoteAddr());
        log.debug("RemoteHost:" + request.getRemoteHost());
        log.debug("Schema:" + request.getScheme());
        log.debug("ServerName:" + request.getServerName());
        log.debug("Cookies[]:" + Arrays.toString(request.getCookies()));
        log.debug("HeaderNames:" + request.getHeaderNames());
        Enumeration<String> headerNames = request.getHeaderNames();
        for (; headerNames.hasMoreElements(); ) {
            String headerName = headerNames.nextElement();
            log.debug("\t HEADER[{}] = {}", headerName, request.getHeader(headerName));
        }
        log.debug("RequestURL:" + request.getRequestURL());
        log.debug("Session:" + request.getSession());
        log.debug("UserPrincipal:" + request.getUserPrincipal());
        log.debug("AttributeNames:" + request.getAttributeNames());
        log.debug("ContentLength:" + request.getContentLength());
        log.debug("DispatcherType:" + request.getDispatcherType());
        log.debug("Locale:" + request.getLocale());
        log.debug("ParameterMap:" + request.getParameterMap());
        log.debug("ServletContext.getRealPath('/'): " + request.getServletContext().getRealPath("/"));
        log.debug("================");
        log.debug("== PARAMETERS ==");
        Map<String, String[]> parmas = request.getParameterMap();
        for (String name : parmas.keySet()) {
            String[] values = parmas.get(name);
            log.debug("{} = {}", name, Arrays.toString(values));
        }
    }

    public static void showSession(HttpSession session, Logger log) {
        Enumeration<String> names = session.getAttributeNames();
        for (; names.hasMoreElements(); ) {
            String name = names.nextElement();
            log.debug("Session[{}] = {}", name, session.getAttribute(name));
        }
    }

    public static void showStack(Logger log) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            log.debug("\n\tClass: {}\n\tMethod:{}\n\tFile: {}, Line:{}", element.getClassName(), element.getMethodName(), element.getFileName(), element.getLineNumber());
        }
    }

    public static String exceptionToString(Exception err) {
        if (err == null) return "";
        final Charset charset = StandardCharsets.UTF_8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = null;
        try {
            ps = new PrintStream(baos, true, charset.name());
            err.printStackTrace(ps);
            return new String(baos.toByteArray(), charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return err.toString();
    }
}
