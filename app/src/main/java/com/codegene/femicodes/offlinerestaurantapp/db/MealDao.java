package com.codegene.femicodes.offlinerestaurantapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.codegene.femicodes.offlinerestaurantapp.model.Meal;
import com.codegene.femicodes.offlinerestaurantapp.model.Menu;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;

/**
 * Created by femicodes on 5/2/2018.
 */

@Dao
public interface MealDao {

    @Query("select * from Meal where id = :id")
    Meal getMealInfo(int id);

    @Query("select * from Meal")
    List<Meal> getAllMeals();

    @Query("select * from Meal where menu_id = :menu_id")
    List<Meal> getAllMealsById(int menu_id);

    @Insert(onConflict = FAIL)
    void addMeal(Meal meal);

}
