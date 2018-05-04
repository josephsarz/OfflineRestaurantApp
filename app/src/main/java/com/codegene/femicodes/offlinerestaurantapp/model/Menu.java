package com.codegene.femicodes.offlinerestaurantapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by femicodes on 5/2/2018.
 */
@Entity(tableName = "menu")
public class Menu {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int image;

    public Menu() {
    }

    public Menu(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
