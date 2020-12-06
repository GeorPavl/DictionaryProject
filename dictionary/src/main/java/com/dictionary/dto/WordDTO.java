package com.dictionary.dto;

import java.util.Date;

public class WordDTO {

    /** DEFINING FIELDS **/

    private Long id;

    private String original;

    private Date createTime;

    private Date updateTime;

    private Boolean isExamined;

    private Long dictionaryId;

    /**
     * DEFINING CONSTRUCTORS:
     *  No arguments constructor
     *  Convert Entity to DTO (default DTO constructor)
     * **/

    public WordDTO() {
    }

    public WordDTO(Long id, String original, Date createTime, Date updateTime, Boolean isExamined, Long dictionaryId) {
        this.id = id;
        this.original = original;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isExamined = isExamined;
        this.dictionaryId = dictionaryId;
    }

    @Override
    public String toString() {
        return "WordDTO{" +
                "id=" + id +
                ", original='" + original + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isExamined=" + isExamined +
                ", dictionaryId=" + dictionaryId +
                '}';
    }

    /** DEFINING GETTERS - SETTERS **/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getExamined() {
        return isExamined;
    }

    public void setExamined(Boolean examined) {
        isExamined = examined;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
}
