package yuhao.entity;

import java.io.Serializable;

public class Article implements Serializable {
    private Integer id;

    private String publishTime;

    private String publishUser;

    private String articleTitle;

    private String articleContent;

    private Integer articleBrowse;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser == null ? null : publishUser.trim();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public Integer getArticleBrowse() {
        return articleBrowse;
    }

    public void setArticleBrowse(Integer articleBrowse) {
        this.articleBrowse = articleBrowse;
    }
}