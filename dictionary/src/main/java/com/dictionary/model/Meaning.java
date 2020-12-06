package com.dictionary.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;

@Entity
@Table(name = "meaning")
public class Meaning {

    /** DEFINE FIELDS **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "definition")
    private String definition;

    @Column(name = "example")
    private String example;

    @JoinColumn(name = "word_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Word word;

    @Column(name = "type")
    private EnumWordType type;

    /**
     * DEFINING ENUM:
     *  enum values
     *  basic constructor (code)
     *  decode (from Integer to Enum)
     *  getCode (from Enum to Integer)
     * **/

    public enum EnumWordType {
        NOUN(0),
        VERB(1),
        ADVERB(2),
        ADJECTIVE(3),
        ELSE(4);

        private Integer code;

        EnumWordType(Integer code){
            this.code = code;
        }

        @JsonCreator
        public static EnumWordType decode(final Integer code){
            return EnumWordType.values()[code];
        }

        @JsonValue
        public Integer getCode(){
            return code;
        }
    }

    /**
     * DEFINING CONSTRUCTORS:
     * No arguments constructor
     * Basic constructor (definition, example, word, type)
     * **/

    public Meaning() {
    }

    public Meaning(String definition, String example, Word word, EnumWordType type) {
        this.definition = definition;
        this.example = example;
        this.word = word;
        this.type = type;
    }

    /**
     * DEFINING METHODS:
     *  Add convenience methods for bi-directional relationship
     *  Add user's dictionary / Set dictionary's user
     *
     *  toString method
     * **/



    /** DEFINE GETTERS - SETTERS **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public EnumWordType getType() {
        return type;
    }

    public void setType(EnumWordType type) {
        this.type = type;
    }
}
