package com.github.iceant.ueditor.spring.boot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.ueditor")
public class UEditorProperties {
    private String serverUrl = "/ueditor/controller";
    private String fileVault = "upload";

    private String imageActionName = "uploadimage";
    private String imageFieldName = "upfile";
    private Long imageMaxSize = 2048000L;
    private String imageAllowFiles = ".png,.jpg,.jpeg,.gif,.bmp";
    private Boolean imageCompressEnable = true;
    private int imageCompressBorder = 1600;
    private String imageInsertAlign = "none";
    private String imageUrlPrefix = "";
    private String imagePathFormat = "/ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";
    /* {filename} 会替换成原文件名,配置这项需要注意中文乱码问题 */
    /* {rand:6} 会替换成随机数,后面的数字是随机数的位数 */
    /* {time} 会替换成时间戳 */
    /* {yyyy} 会替换成四位年份 */
    /* {yy} 会替换成两位年份 */
    /* {mm} 会替换成两位月份 */
    /* {dd} 会替换成两位日期 */
    /* {hh} 会替换成两位小时 */
    /* {ii} 会替换成两位分钟 */
    /* {ss} 会替换成两位秒 */
    /* 非法字符 \ : * ? " < > | */
    /* 具请体看线上文档: fex.baidu.com/ueditor/#use-format_upload_filename */

    private String scrawlActionName = "uploadscrawl";
    private String scrawlFieldName = "upfile";
    private String scrawlPathFormat = "/ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,可以自定义保存路径和文件名格式 */
    private Long scrawlMaxSize = 2048000L; /* 上传大小限制，单位B */
    private String scrawlUrlPrefix = "";
    private String scrawlInsertAlign = "none";

    private String snapscreenActionName = "uploadimage";
    private String snapscreenPathFormat = "/ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";
    private String snapscreenUrlPrefix = "";
    private String snapscreenInsertAlign = "none";

    private String catcherLocalDomain = "127.0.0.1,localhost,img.baidu.com";
    private String catcherActionName = "catchimage";
    private String catcherFieldName = "source";
    private String catcherPathFormat = "/ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";
    private String catcherUrlPrefix = "";
    private Long catcherMaxSize = 2048000L;
    private String catcherAllowFiles = ".png,.jpg,.jpeg,.gif,.bmp";

    private String videoActionName = "uploadvideo";
    private String videoFieldName = "upfile";
    private String videoPathFormat = "/ueditor/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}";
    private String videoUrlPrefix = "";
    private Long videoMaxSize = 102400000L;
    private String videoAllowFiles = ".flv,.swf,.mkv,.avi,.rm,.rmvb,.mpeg,.mpg,.ogg,.ogv,.mov,.wmv,.mp4,.webm,.mp3,.wav,.mid";

    private String fileActionName = "uploadfile";
    private String fileFieldName = "upfile";
    private String filePathFormat = "/ueditor/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}";
    private String fileUrlPrefix = "";
    private Long fileMaxSize = 51200000L;
    private String fileAllowFiles = ".png,.jpg,.jpeg,.gif,.bmp,.flv,.swf,.mkv,.avi,.rm,.rmvb,.mpeg,.mpg,.ogg,.ogv,.mov,.wmv,.mp4,.webm,.mp3,.wav,.mid,.rar,.zip,.tar,.gz,.7z,.bz2,.cab,.iso,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.pdf,.txt,.md,.xml";

    private String imageManagerActionName = "listimage";
    private String imageManagerListPath = "/ueditor/upload/image/";
    private int imageManagerListSize = 20;
    private String imageManagerUrlPrefix = "";
    private String imageManagerInsertAlign = "none";
    private String imageManagerAllowFiles = ".png,.jpg,.jpeg,.gif,.bmp";

    private String fileManagerActionName = "listfile";
    private String fileManagerListPath = "/ueditor/upload/file/";
    private String fileManagerUrlPrefix = "";
    private int fileManagerListSize = 20;
    private String fileManagerAllowFiles = ".png,.jpg,.jpeg,.gif,.bmp,.flv,.swf,.mkv,.avi,.rm,.rmvb,.mpeg,.mpg,.ogg,.ogv,.mov,.wmv,.mp4,.webm,.mp3,.wav,.mid,.rar,.zip,.tar,.gz,.7z,.bz2,.cab,.iso,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.pdf,.txt,.md,.xml";

    public String getFileVault() {
        return fileVault;
    }

    public void setFileVault(String fileVault) {
        this.fileVault = fileVault;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getImageActionName() {
        return imageActionName;
    }

    public void setImageActionName(String imageActionName) {
        this.imageActionName = imageActionName;
    }

    public String getImageFieldName() {
        return imageFieldName;
    }

    public void setImageFieldName(String imageFieldName) {
        this.imageFieldName = imageFieldName;
    }

    public void setImageMaxSize(Long imageMaxSize) {
        this.imageMaxSize = imageMaxSize;
    }

    public long getImageMaxSize() {
        return imageMaxSize;
    }

    public String getImageAllowFiles() {
        return imageAllowFiles;
    }

    public void setImageAllowFiles(String imageAllowFiles) {
        this.imageAllowFiles = imageAllowFiles;
    }

    public Boolean getImageCompressEnable() {
        return imageCompressEnable;
    }

    public void setImageCompressEnable(Boolean imageCompressEnable) {
        this.imageCompressEnable = imageCompressEnable;
    }

    public int getImageCompressBorder() {
        return imageCompressBorder;
    }

    public void setImageCompressBorder(int imageCompressBorder) {
        this.imageCompressBorder = imageCompressBorder;
    }

    public String getImageInsertAlign() {
        return imageInsertAlign;
    }

    public void setImageInsertAlign(String imageInsertAlign) {
        this.imageInsertAlign = imageInsertAlign;
    }

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }

    public void setImageUrlPrefix(String imageUrlPrefix) {
        this.imageUrlPrefix = imageUrlPrefix;
    }

    public String getImagePathFormat() {
        return imagePathFormat;
    }

    public void setImagePathFormat(String imagePathFormat) {
        this.imagePathFormat = imagePathFormat;
    }

    public String getScrawlActionName() {
        return scrawlActionName;
    }

    public void setScrawlActionName(String scrawlActionName) {
        this.scrawlActionName = scrawlActionName;
    }

    public String getScrawlFieldName() {
        return scrawlFieldName;
    }

    public void setScrawlFieldName(String scrawlFieldName) {
        this.scrawlFieldName = scrawlFieldName;
    }

    public String getScrawlPathFormat() {
        return scrawlPathFormat;
    }

    public void setScrawlPathFormat(String scrawlPathFormat) {
        this.scrawlPathFormat = scrawlPathFormat;
    }

    public Long getScrawlMaxSize() {
        return scrawlMaxSize;
    }

    public void setScrawlMaxSize(Long scrawlMaxSize) {
        this.scrawlMaxSize = scrawlMaxSize;
    }

    public String getScrawlUrlPrefix() {
        return scrawlUrlPrefix;
    }

    public void setScrawlUrlPrefix(String scrawlUrlPrefix) {
        this.scrawlUrlPrefix = scrawlUrlPrefix;
    }

    public String getScrawlInsertAlign() {
        return scrawlInsertAlign;
    }

    public void setScrawlInsertAlign(String scrawlInsertAlign) {
        this.scrawlInsertAlign = scrawlInsertAlign;
    }

    public String getSnapscreenActionName() {
        return snapscreenActionName;
    }

    public void setSnapscreenActionName(String snapscreenActionName) {
        this.snapscreenActionName = snapscreenActionName;
    }

    public String getSnapscreenPathFormat() {
        return snapscreenPathFormat;
    }

    public void setSnapscreenPathFormat(String snapscreenPathFormat) {
        this.snapscreenPathFormat = snapscreenPathFormat;
    }

    public String getSnapscreenUrlPrefix() {
        return snapscreenUrlPrefix;
    }

    public void setSnapscreenUrlPrefix(String snapscreenUrlPrefix) {
        this.snapscreenUrlPrefix = snapscreenUrlPrefix;
    }

    public String getSnapscreenInsertAlign() {
        return snapscreenInsertAlign;
    }

    public void setSnapscreenInsertAlign(String snapscreenInsertAlign) {
        this.snapscreenInsertAlign = snapscreenInsertAlign;
    }

    public String getCatcherLocalDomain() {
        return catcherLocalDomain;
    }

    public void setCatcherLocalDomain(String catcherLocalDomain) {
        this.catcherLocalDomain = catcherLocalDomain;
    }

    public String getCatcherActionName() {
        return catcherActionName;
    }

    public void setCatcherActionName(String catcherActionName) {
        this.catcherActionName = catcherActionName;
    }

    public String getCatcherFieldName() {
        return catcherFieldName;
    }

    public void setCatcherFieldName(String catcherFieldName) {
        this.catcherFieldName = catcherFieldName;
    }

    public String getCatcherPathFormat() {
        return catcherPathFormat;
    }

    public void setCatcherPathFormat(String catcherPathFormat) {
        this.catcherPathFormat = catcherPathFormat;
    }

    public String getCatcherUrlPrefix() {
        return catcherUrlPrefix;
    }

    public void setCatcherUrlPrefix(String catcherUrlPrefix) {
        this.catcherUrlPrefix = catcherUrlPrefix;
    }

    public Long getCatcherMaxSize() {
        return catcherMaxSize;
    }

    public void setCatcherMaxSize(Long catcherMaxSize) {
        this.catcherMaxSize = catcherMaxSize;
    }

    public String getCatcherAllowFiles() {
        return catcherAllowFiles;
    }

    public void setCatcherAllowFiles(String catcherAllowFiles) {
        this.catcherAllowFiles = catcherAllowFiles;
    }

    public String getVideoActionName() {
        return videoActionName;
    }

    public void setVideoActionName(String videoActionName) {
        this.videoActionName = videoActionName;
    }

    public String getVideoFieldName() {
        return videoFieldName;
    }

    public void setVideoFieldName(String videoFieldName) {
        this.videoFieldName = videoFieldName;
    }

    public String getVideoPathFormat() {
        return videoPathFormat;
    }

    public void setVideoPathFormat(String videoPathFormat) {
        this.videoPathFormat = videoPathFormat;
    }

    public String getVideoUrlPrefix() {
        return videoUrlPrefix;
    }

    public void setVideoUrlPrefix(String videoUrlPrefix) {
        this.videoUrlPrefix = videoUrlPrefix;
    }

    public Long getVideoMaxSize() {
        return videoMaxSize;
    }

    public void setVideoMaxSize(Long videoMaxSize) {
        this.videoMaxSize = videoMaxSize;
    }

    public String getVideoAllowFiles() {
        return videoAllowFiles;
    }

    public void setVideoAllowFiles(String videoAllowFiles) {
        this.videoAllowFiles = videoAllowFiles;
    }

    public String getFileActionName() {
        return fileActionName;
    }

    public void setFileActionName(String fileActionName) {
        this.fileActionName = fileActionName;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    public String getFilePathFormat() {
        return filePathFormat;
    }

    public void setFilePathFormat(String filePathFormat) {
        this.filePathFormat = filePathFormat;
    }

    public String getFileUrlPrefix() {
        return fileUrlPrefix;
    }

    public void setFileUrlPrefix(String fileUrlPrefix) {
        this.fileUrlPrefix = fileUrlPrefix;
    }

    public Long getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(Long fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
    }

    public String getFileAllowFiles() {
        return fileAllowFiles;
    }

    public void setFileAllowFiles(String fileAllowFiles) {
        this.fileAllowFiles = fileAllowFiles;
    }

    public String getImageManagerActionName() {
        return imageManagerActionName;
    }

    public void setImageManagerActionName(String imageManagerActionName) {
        this.imageManagerActionName = imageManagerActionName;
    }

    public String getImageManagerListPath() {
        return imageManagerListPath;
    }

    public void setImageManagerListPath(String imageManagerListPath) {
        this.imageManagerListPath = imageManagerListPath;
    }

    public int getImageManagerListSize() {
        return imageManagerListSize;
    }

    public void setImageManagerListSize(int imageManagerListSize) {
        this.imageManagerListSize = imageManagerListSize;
    }

    public String getImageManagerUrlPrefix() {
        return imageManagerUrlPrefix;
    }

    public void setImageManagerUrlPrefix(String imageManagerUrlPrefix) {
        this.imageManagerUrlPrefix = imageManagerUrlPrefix;
    }

    public String getImageManagerInsertAlign() {
        return imageManagerInsertAlign;
    }

    public void setImageManagerInsertAlign(String imageManagerInsertAlign) {
        this.imageManagerInsertAlign = imageManagerInsertAlign;
    }

    public String getImageManagerAllowFiles() {
        return imageManagerAllowFiles;
    }

    public void setImageManagerAllowFiles(String imageManagerAllowFiles) {
        this.imageManagerAllowFiles = imageManagerAllowFiles;
    }

    public String getFileManagerActionName() {
        return fileManagerActionName;
    }

    public void setFileManagerActionName(String fileManagerActionName) {
        this.fileManagerActionName = fileManagerActionName;
    }

    public String getFileManagerListPath() {
        return fileManagerListPath;
    }

    public void setFileManagerListPath(String fileManagerListPath) {
        this.fileManagerListPath = fileManagerListPath;
    }

    public String getFileManagerUrlPrefix() {
        return fileManagerUrlPrefix;
    }

    public void setFileManagerUrlPrefix(String fileManagerUrlPrefix) {
        this.fileManagerUrlPrefix = fileManagerUrlPrefix;
    }

    public int getFileManagerListSize() {
        return fileManagerListSize;
    }

    public void setFileManagerListSize(int fileManagerListSize) {
        this.fileManagerListSize = fileManagerListSize;
    }

    public String getFileManagerAllowFiles() {
        return fileManagerAllowFiles;
    }

    public void setFileManagerAllowFiles(String fileManagerAllowFiles) {
        this.fileManagerAllowFiles = fileManagerAllowFiles;
    }
}