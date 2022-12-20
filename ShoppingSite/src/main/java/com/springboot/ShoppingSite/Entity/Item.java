package com.springboot.ShoppingSite.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "post")
    private String text;

    @Lob
    @Column(name = "img")
    private byte[] image;

    private int category_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getCategory_id() {
        return category_id;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
