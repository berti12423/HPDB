package com.example.hospitalproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
public class ReactionListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Reaction reaction;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private List<Reaction> reactionList;
    private ReactionAdapter adapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_list);
        drawerLayout = findViewById(R.id.drawer_layout_reaction_list);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view_nurse);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Create a list of Reaction objects
        reactionList = new ArrayList<>();
        // Add your Reaction objects to the list
        Reaction newReaction = (Reaction) getIntent().getSerializableExtra("REACTION_OBJECT");
        Reaction newReactionSend= (Reaction) getIntent().getSerializableExtra("NEWREACTIONSEND_OBJECT");
        if (newReaction != null) {
            // Check if the list already contains a reaction with the same code
            boolean reactionExists = false;
            for (int i = 0; i < reactionList.size(); i++) {
                Reaction existingReaction = reactionList.get(i);
                if (existingReaction.getCode().equals(newReaction.getCode())) {
                    // A reaction with the same code already exists
                    reactionExists = true;
                    // Replace the existing reaction with the new one
                    reactionList.set(i, newReaction);
                    break; // Exit the loop since we found a match
                }
            }
            // If the reaction doesn't exist, add it to the list
            if (!reactionExists) {
                reactionList.add(newReaction);
            }
        }
            reactionList.add(new Reaction("Code1", 1, "Date1", "Time1", "Description1"));
            reactionList.add(new Reaction("Code2", 5, "Date2", "Time2", "Description2"));
            reactionList.add(new Reaction("Code3", 4, "Date3", "Time3", "Description3"));
            reactionList.add(newReactionSend);

        // Create and set up the adapter
        adapter = new ReactionAdapter(reactionList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (item.getItemId() == R.id.nav_information) {
            intent = new Intent(this, information.class);
            intent.putExtra("REACTION_OBJECT", reaction);
            startActivity(intent);
        }
        // Close the drawer after handling the click
        drawerLayout.closeDrawer(GravityCompat.START);
        return true; // Return true to indicate the item has been handled
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_reaction_list, menu);
        return true;
    }

}