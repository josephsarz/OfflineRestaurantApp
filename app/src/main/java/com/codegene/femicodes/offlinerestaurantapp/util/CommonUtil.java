package com.codegene.femicodes.offlinerestaurantapp.util;

import android.content.Context;
import android.widget.EditText;

import com.codegene.femicodes.offlinerestaurantapp.db.AppDatabase;
import com.codegene.femicodes.offlinerestaurantapp.model.Meal;
import com.codegene.femicodes.offlinerestaurantapp.model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by femicodes on 5/2/2018.
 */

public class CommonUtil {




    public CommonUtil() {

    }


    public static boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    public static void populateMenuDatabase(Context context) {


        List<Menu> mMenuList = DataGenerator.generateMenu();

        //save to db
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < mMenuList.size(); i++) {
                Menu menu = mMenuList.get(i);
                AppDatabase.getDatabase(context).mMenuDao().addMenu(menu);
            }

        });

    }


    public static void populateMealDatabase(Context context) {


        List<Meal> mMealList = DataGenerator.generateMeals();

        //save to db
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < mMealList.size(); i++) {
                Meal meal = mMealList.get(i);
                AppDatabase.getDatabase(context).mMealDao().addMeal(meal);
            }

        });

    }


}
