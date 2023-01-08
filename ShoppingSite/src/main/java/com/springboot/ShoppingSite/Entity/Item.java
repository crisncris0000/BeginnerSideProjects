package com.springboot.ShoppingSite.Entity;

import javax.persistence.*;

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
    private String image;


    @OneToOne(cascade = {CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.PERSIST,
                        CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Transient
    public String getImagePath(){
        if(image == null) return null;

        return "/item-images/" + id + "/" + image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", image=" + image +
                ", category=" + category +
                '}';
    }
}
