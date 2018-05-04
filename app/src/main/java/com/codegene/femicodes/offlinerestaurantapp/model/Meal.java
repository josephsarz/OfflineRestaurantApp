package com.codegene.femicodes.offlinerestaurantapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by femicodes on 5/2/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Menu.class,
        parentColumns = "id",
        childColumns = "menu_id",
        onDelete = ForeignKey.CASCADE),
        indices={@Index(value="menu_id")
})
public class Meal {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int image;
    private float price;

    @ColumnInfo(name = "menu_id")
    private int menu_id;

    public Meal(int id, String name, String description, int image, float price, int menu_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.menu_id = menu_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
}
