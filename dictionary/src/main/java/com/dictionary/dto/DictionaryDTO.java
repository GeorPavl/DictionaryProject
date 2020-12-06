package com.dictionary.dto;

import com.dictionary.model.Dictionary;

import java.util.Date;
import java.util.List;

public class DictionaryDTO {

    /** DEFINING FIELDS **/

    private Long id;

    private String language;

    private Date createTime;

    private Date updateTime;

    private Long userId;

    private List<WordDTO> words;

    /**
     * DEFINING CONSTRUCTORS:
     *  No arguments constructor
     *  Convert Entity to DTO (default DTO constructor)
     * **/

    public DictionaryDTO() {
    }

    public DictionaryDTO(Dictionary dictionary){
        this.id = dictionary.getId();
        this.language = dictionary.getLanguage();
        this.createTime = dictionary.getCreateTime();
        this.updateTime = dictionary.getUpdateTime();
        this.userId = dictionary.getUser().getId();
    }

    /** DEFINING GETTERS - SETTERS **/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<WordDTO> getWords() {
        return words;
    }

    public void setWords(List<WordDTO> words) {
        this.words = words;
    }
}
