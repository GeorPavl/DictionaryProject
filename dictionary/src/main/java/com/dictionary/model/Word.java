package com.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "word")
public class Word {

    /** DEFINING FIELDS **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "original")
    private String original;

    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    private Date updateTime;

    @Column(name = "is_examined")
    private Boolean isExamined;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_id")
    @JsonIgnore
    private Dictionary dictionary;

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "word",
               cascade = CascadeType.ALL)
    private List<Meaning> meanings;

    /**
     * DEFINING CONSTRUCTORS:
     * No arguments constructor
     * Basic constructor (original, isExamined,dictionary)
     * **/

    public Word() {
    }

    public Word(String original, Boolean isExamined, Dictionary dictionary) {
        this.original = original;
        this.isExamined = isExamined;
        this.dictionary = dictionary;
    }

    /**
     * DEFINING METHODS:
     *  Add convenience methods for bi-directional relationship
     *  Add user's dictionary / Set dictionary's user
     *
     *  toString method
     * **/

    public void addMeaning(Meaning meaning){
        if (meanings == null){
            meanings = new ArrayList<>();
        }
        meanings.add(meaning);
        meaning.setWord(this);
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

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Boolean getExamined() {
        return isExamined;
    }

    public void setExamined(Boolean examined) {
        isExamined = examined;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
}
