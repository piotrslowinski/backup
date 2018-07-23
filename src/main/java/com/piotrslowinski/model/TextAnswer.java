package com.piotrslowinski.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="text_answer")
public class TextAnswer extends Answer {

    private String value;

    public TextAnswer() {}

    public TextAnswer(Long id, String value) {
        super(id);
        this.value = value;
    }

    public TextAnswer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
