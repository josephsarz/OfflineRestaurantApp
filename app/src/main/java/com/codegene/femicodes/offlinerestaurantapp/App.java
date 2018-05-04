package com.codegene.femicodes.offlinerestaurantapp;

import android.app.Application;

/**
 * Created by femicodes on 5/2/2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        populateDatabaseWithTestData();
    }

    public void populateDatabaseWithTestData(){

    }


}
