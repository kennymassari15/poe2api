package com.kmassari.poe2_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String ascendancy1;
    private String ascendancy2;
    private String ascendancy3;


    public Character() {
    }

    public Character(String name, String ascendancy1, String ascendancy2, String ascendancy3, String image) {
        this.name = name;
        this.ascendancy1 = ascendancy1;
        this.ascendancy2 = ascendancy2;
        this.ascendancy3 = ascendancy3;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAscendancy1() {
        return ascendancy1;
    }

    public void setAscendancy1(String ascendancy1) {
        this.ascendancy1 = ascendancy1;
    }

    public String getAscendancy2() {
        return ascendancy2;
    }

    public void setAscendancy2(String ascendancy2) {
        this.ascendancy2 = ascendancy2;
    }

    public String getAscendancy3() {
        return ascendancy3;
    }

    public void setAscendancy3(String ascendancy3) {
        this.ascendancy3 = ascendancy3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
