package com.piotrslowinski.model;

import javax.persistence.*;

@Entity
@Table(name="boolean_answer")
public class BooleanAnswer extends Answer {

    private String answerTitle;

    private boolean value;

    public BooleanAnswer() {}

    public BooleanAnswer(Long id, String answerTitle, boolean value) {
        super(id);
        this.answerTitle = answerTitle;
        this.value = value;
    }

    public BooleanAnswer(String title, boolean value) {
        this.answerTitle = title;
        this.value = value;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
