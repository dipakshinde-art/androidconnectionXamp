package com.example.toure;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //  Toolbar toolbar;
    ImageView profile_imageView;
    TextView profile_name_textView, profile_email_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        navigationView = findViewById(R.id.nav_view);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile_imageView = findViewById(R.id.profile_imageView);
        profile_name_textView = findViewById(R.id.profile_name_text);
        profile_email_textView = findViewById(R.id.profile_email);

        navigationView.bringToFront();

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }

    //when pressed back
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            //  Menu menu = navigationView.getMenu();
            case R.id.nav_home:
                break;

            case R.id.nav_profile:
                Intent intent = new Intent(HomePage.this, Profile.class);
                startActivity(intent);
                break;

            case R.id.nav_signout:
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.nav_signout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_LoggedIn).setVisible(true);
                break;

            case R.id.nav_LoggedIn:
                Menu Menu = navigationView.getMenu();
                Menu.findItem(R.id.nav_profile).setVisible(true);
                Menu.findItem(R.id.nav_signout).setVisible(true);
                Menu.findItem(R.id.nav_LoggedIn).setVisible(false);
                break;

            case R.id.nav_shair:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}

