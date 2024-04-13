package com.example.hospitalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class information extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Reaction reaction;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        reaction = (Reaction) getIntent().getSerializableExtra("REACTION_OBJECT");
        drawerLayout = findViewById(R.id.drawer_layout_information);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view_nurse);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation item clicks here
        if (item.getItemId() == R.id.nav_reaction) {
            intent = new Intent(this, nurseMainPage.class);
            intent.putExtra("REACTION_OBJECT", reaction);
            startActivity(intent);
        } else if (item.getItemId() == R.id.nav_personal_reaction) {
            intent = new Intent(this, ChildActivity.class);
            intent.putExtra("REACTION_OBJECT", reaction);
            startActivity(intent);
        } else if (item.getItemId() == R.id.nav_reactionList) {
            intent = new Intent(this, ReactionListActivity.class);
            intent.putExtra("REACTION_OBJECT", reaction);
            startActivity(intent);
        } else if (item.getItemId() == R.id.nav_information) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        // Close the drawer after handling the click
        drawerLayout.closeDrawer(GravityCompat.START);

        return true; // Return true to indicate the item has been handled
    }
}