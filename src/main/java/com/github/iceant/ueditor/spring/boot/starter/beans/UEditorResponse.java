package com.github.iceant.ueditor.spring.boot.starter.beans;

import java.io.Serializable;

public class UEditorResponse implements Serializable {
    public static final String STATE_SUCCESS = "SUCCESS";

    private String state;
    private String url;
    private String title;
    private String original;

    public String getState() {
        return state;
    }

    public UEditorResponse setState(String state) {
        this.state = state;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public UEditorResponse setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UEditorResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getOriginal() {
        return original;
    }

    public UEditorResponse setOriginal(String original) {
        this.original = original;
        return this;
    }
}
