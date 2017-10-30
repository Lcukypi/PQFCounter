package com.luckypi.pqfcounterfinal.news;

/**
 * Created by dell on 2017/6/6.
 * 创建新闻信息类
 */

public class NewsInfo {
    private String icon;
    private String title;
    private String content;
    private int type;
    private long comment;
    private String message;
    public NewsInfo(String icon, String title, String content, int type, long comment, String message){
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.type = type;
        this.comment = comment;
        this.message = message;
    }
    public String getMessage(){return  message;}

    public  void setMessage(String message){this.message=message;}

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
    }
}
