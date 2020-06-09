package com.github.iceant.ueditor.spring.boot.starter.services;

public class FilesizeLimitExceededException extends RuntimeException{
    private long limitFilesize;
    private long realFilesize;

    public FilesizeLimitExceededException(long limitFilesize, long realFilesize) {
        this.limitFilesize = limitFilesize;
        this.realFilesize = realFilesize;
    }

    public FilesizeLimitExceededException(String message, long limitFilesize, long realFilesize) {
        super(message);
        this.limitFilesize = limitFilesize;
        this.realFilesize = realFilesize;
    }

    public FilesizeLimitExceededException(String message, Throwable cause, long limitFilesize, long realFilesize) {
        super(message, cause);
        this.limitFilesize = limitFilesize;
        this.realFilesize = realFilesize;
    }

    public FilesizeLimitExceededException(Throwable cause, long limitFilesize, long realFilesize) {
        super(cause);
        this.limitFilesize = limitFilesize;
        this.realFilesize = realFilesize;
    }

    public FilesizeLimitExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, long limitFilesize, long realFilesize) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.limitFilesize = limitFilesize;
        this.realFilesize = realFilesize;
    }

    public long getLimitFilesize() {
        return limitFilesize;
    }

    public void setLimitFilesize(long limitFilesize) {
        this.limitFilesize = limitFilesize;
    }

    public long getRealFilesize() {
        return realFilesize;
    }

    public void setRealFilesize(long realFilesize) {
        this.realFilesize = realFilesize;
    }
}
