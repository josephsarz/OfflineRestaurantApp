package com.codegene.femicodes.offlinerestaurantapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codegene.femicodes.offlinerestaurantapp.ui.fragment.MenuCategoryFragment;
import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.ui.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }

    private void displaySelectedScreen(int id) {

        //creating fragment object
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_menu_category) {
            fragment = new MenuCategoryFragment();
        } else if (id == R.id.nav_order_history) {
            //fragment = new OrderHistoryFragment();
        } else if (id == R.id.nav_favorites) {
            //fragment = new FavoriteMenuFragment();
        } else if (id == R.id.nav_hot_deals) {
//            Intent hotDealIntent = new Intent(MainActivity.this, HotDealActvity.class);
//            startActivity(hotDealIntent);
        }
        else if (id == R.id.nav_notification) {
//            Intent notificationIntent = new Intent(MainActivity.this, NotificationActivity.class);
//            startActivity(notificationIntent);
        }
        else if (id == R.id.nav_profile) {
            //fragment = new ProfileFragment();
        }else if (id == R.id.nav_logout) {
            //remove user data from shared preference
//            SharedPreferences mShared = ((CustomApplication)getApplication()).getShared().getInstanceOfSharedPreference();
//            mShared.edit().clear().apply();
//
//            //Navigate to login page
//            Intent loginPageIntent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(loginPageIntent);
//            finish();
        }


        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
