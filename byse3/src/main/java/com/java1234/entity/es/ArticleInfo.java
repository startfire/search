package com.java1234.entity.es;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 资源ES索引实体
 * @author fangjian
 */
@Document(indexName = "testdemo",type = "my")
public class ArticleInfo implements Serializable {

    private Long id; // 编号

    private String name; // 文章名称

    private String content; // 文章内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
