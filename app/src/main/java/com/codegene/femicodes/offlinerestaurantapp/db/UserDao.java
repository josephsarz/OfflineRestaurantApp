package com.codegene.femicodes.offlinerestaurantapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.codegene.femicodes.offlinerestaurantapp.model.User;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by femicodes on 5/1/2018.
 */

@Dao
public interface UserDao {

    @Query("select * from User where username = :username")
    User getUserInfo(String username);

    @Insert(onConflict = FAIL)
    void addUser(User user);

}
