package com.codegene.femicodes.offlinerestaurantapp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codegene.femicodes.offlinerestaurantapp.model.Meal;
import com.codegene.femicodes.offlinerestaurantapp.model.Menu;
import com.codegene.femicodes.offlinerestaurantapp.model.User;

/**
 * Created by femicodes on 5/1/2018.
 */

@Database(entities = {User.class, Menu.class, Meal.class}, version = 10, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "restaurant_db";

    private static RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("status", "first time open db");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d("status", "open db");
        }
    };

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME
                            )
                            .addCallback(rdc)
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }


    public abstract UserDao mUserDao();
    public abstract MenuDao mMenuDao();
    public abstract MealDao mMealDao();

}

