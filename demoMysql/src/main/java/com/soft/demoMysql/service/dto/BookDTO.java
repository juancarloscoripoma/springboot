package com.soft.demoMysql.service.dto;

import java.io.Serializable;

public class BookDTO implements Serializable {

    private Long id;
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
