package com.codegene.femicodes.offlinerestaurantapp.util;

import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.model.Meal;
import com.codegene.femicodes.offlinerestaurantapp.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by femicodes on 5/3/2018.
 */

class DataGenerator {


    static List<Menu> generateMenu() {
        List<Menu> menuItems = new ArrayList<>();
        menuItems.add(new Menu(1, "Salad Menu", R.drawable.food1 ));

        menuItems.add(new Menu(2, "Dessert Menu", R.drawable.food2));

        menuItems.add(new Menu(3, "Noodles Menu", R.drawable.food3));

        menuItems.add(new Menu(4, "Potatoes Menu", R.drawable.food4));

        menuItems.add(new Menu(5, "Burger Menu", R.drawable.food5));

        return menuItems;
    }


    static List<Meal> generateMeals() {
        List<Meal> mealItems = new ArrayList<>();

        mealItems.add(new Meal(1, "Fied Rice with Salad" , "p", R.drawable.food1, 800f, 1));

        mealItems.add(new Meal(2, "Vegetable Salad" , "p", R.drawable.food1, 250f, 1));

        mealItems.add(new Meal(3, "Ice cream Cake", "p", R.drawable.food2, 400f, 2));

        mealItems.add(new Meal(4, "Milk Shake", "p", R.drawable.food2, 350f, 2));

        mealItems.add(new Meal(5, "Spaggetti Delight","p", R.drawable.food3, 550f, 3));

        mealItems.add(new Meal(6, "Indomie And Egg","p", R.drawable.food3, 250f, 3));

        mealItems.add(new Meal(7, "Potato Poraro ", "p",R.drawable.food4, 250f, 4));

        mealItems.add(new Meal(8,  "Plastered Potato Chips","p", R.drawable.food4, 250f, 4));

        mealItems.add(new Meal(9,  "Submarine Burger", "p",R.drawable.food5, 500f, 5));

        mealItems.add(new Meal(10, "Fish Burger","p", R.drawable.food5, 450f, 5));

        mealItems.add(new Meal(11, "Non Fat Burger Splash","p", R.drawable.food5, 450f, 5));

        return mealItems;
    }


}