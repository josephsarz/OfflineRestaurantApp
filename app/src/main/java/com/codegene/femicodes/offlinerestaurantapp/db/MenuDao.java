package com.codegene.femicodes.offlinerestaurantapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.codegene.femicodes.offlinerestaurantapp.model.Menu;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;

/**
 * Created by femicodes on 5/2/2018.
 */
@Dao
public interface MenuDao {

    @Query("select * from Menu where id = :id")
    Menu getMenuInfo(int id);

    @Query("select * from Menu")
    List<Menu> getAllMenu();

    @Insert(onConflict = FAIL)
    void addMenu(Menu menu);

}
