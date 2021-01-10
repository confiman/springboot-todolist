package com.modanisa.devopschallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    private int id;
    private String message;
    private Boolean isActive;

    public Todo() {
    }

    public Todo(int id, String message, Boolean isActive) {
        this.id = id;
        this.message = message;
        this.isActive = isActive;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public Boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive=isActive;
    }

}
