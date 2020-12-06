package com.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dictionary")
public class Dictionary {

    /** DEFINING FIELDS **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "language")
    private String language;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY,
              mappedBy = "dictionary",
              cascade = CascadeType.ALL,
              orphanRemoval = true)
    private List<Word> words;

    /**
     * DEFINING CONSTRUCTORS:
     * No arguments constructor
     * Basic constructor (language, user)
     * **/

    public Dictionary(){}

    public Dictionary(String language, User user) {
        this.language = language;
        this.user = user;
    }

    /**
     * DEFINING METHODS:
     *  Add convenience methods for bi-directional relationship
     *  Add user's dictionary / Set dictionary's user
     *
     *  toString method
     * **/

    public void addWord(Word word){
        if (words == null){
            words = new ArrayList<>();
        }
        words.add(word);
        word.setDictionary(this);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", user=" + user +
                '}';
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
