package com.java1234.entity;


import javax.persistence.*;

/**
 * 文章资源实体
 * @author fangjian
 */

@Entity
@Table(name="article")
public class ArticleWithBLOBs {
    @Id
    @Column(name="a_id")
    private Integer id;

    private String title;
    @Column(name="pub_time")
    private String pubTime;

    private Integer pmid;

    private String source;

    private String type;

    private String keywords;
    @Column(name="create_time")
    private Integer createTime;
    @Column(name="update_time")
    private Integer updateTime;

    private String author;
    @Column(name="author_info")
    private String authorInfo;

    private String abstracttext;

    private int uploadtype;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo == null ? null : authorInfo.trim();
    }

    public String getAbstracttext() {
        return abstracttext;
    }

    public void setAbstracttext(String abstracttext) {
        this.abstracttext = abstracttext == null ? null : abstracttext.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public int getUploadtype() {
        return uploadtype;
    }

    public void setUploadtype(int uploadtype) {
        this.uploadtype = uploadtype;
    }


}