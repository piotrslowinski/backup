package com.piotrslowinski.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {

    @Id
    private String value;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private boolean active;

    @ManyToOne
    private User user;

    @ManyToOne
    private Survey survey;


    public Token() {
    }

    public Token(User user, Survey survey) {
        this.value = UUID.randomUUID().toString();
        this.expiryDate = LocalDate.now().plusDays(30);
        this.active = true;
        this.user = user;
        this.survey = survey;
    }

    public boolean isValid() {
        return active && !isExpired();
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
