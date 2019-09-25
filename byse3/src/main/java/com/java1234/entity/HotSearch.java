package com.java1234.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 热门搜索实体
 * @author fangjian
 */
@Entity
@Table(name="t_hotSearch")
public class HotSearch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 编号

    @Column(length=100)
    private String name; // 热门搜索词

    private Integer sort; // 排序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
