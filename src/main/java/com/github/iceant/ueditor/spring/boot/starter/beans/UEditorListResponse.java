package com.github.iceant.ueditor.spring.boot.starter.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UEditorListResponse implements Serializable {
    public static class UEditorListResponseItem implements Serializable{
        private String url;

        public UEditorListResponseItem() {
        }

        public UEditorListResponseItem(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    private String state;
    private List<UEditorListResponseItem> list = new LinkedList<>();
    private Integer start;
    private Integer total;

    public String getState() {
        return state;
    }

    public UEditorListResponse setState(String state) {
        this.state = state;
        return this;
    }

    public List<UEditorListResponseItem> getList() {
        return list;
    }

    public UEditorListResponse setList(List<UEditorListResponseItem> list) {
        this.list = list;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public UEditorListResponse setStart(Integer start) {
        this.start = start;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public UEditorListResponse setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public UEditorListResponse addItem(UEditorListResponseItem item){
        list.add(item);
        return this;
    }

    public UEditorListResponse addItemURL(String url){
        list.add(new UEditorListResponseItem(url));
        return this;
    }
}
