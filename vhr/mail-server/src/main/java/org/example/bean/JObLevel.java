package org.example.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class JObLevel {
    private Integer id;

    private String name;

    private String titleLevel;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="Asia/ShangHai")
    private Date createDate;

    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public JObLevel() {
    }

    @Override
    public String toString() {
        return "JObLevel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", titleLevel='" + titleLevel + '\'' +
                ", createDate=" + createDate +
                ", enabled=" + enabled +
                '}';
    }

    public JObLevel(String name, String titleLevel, Date createDate, Boolean enabled) {
        this.name = name;
        this.titleLevel = titleLevel;
        this.createDate = createDate;
        this.enabled = enabled;
    }

    public JObLevel(Integer id, String name, String titleLevel, Date createDate, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.titleLevel = titleLevel;
        this.createDate = createDate;
        this.enabled = enabled;
    }
}