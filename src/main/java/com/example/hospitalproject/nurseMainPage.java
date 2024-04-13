package com.example.hospitalproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class  nurseMainPage extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private Reaction reaction,newReactionSend;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Spinner description;
    private EditText editTextDate,editTextTime;
    private Button btnSend,btnAdd;
    private Intent intent;
    private Dialog d;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_main_page);
        btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        reaction = (Reaction) getIntent().getSerializableExtra("REACTION_OBJECT");
        //dialog
        d=new Dialog(this);
        d.setContentView(R.layout.add_reaction_dialog);
        d.setCancelable(true);
        editTextTime=(EditText)d.findViewById(R.id.editTextTime);
        editTextDate=(EditText)d.findViewById(R.id.editTextDate);
        description=(Spinner)d.findViewById((R.id.descriptionSpinner));
        btnSend=(Button)d.findViewById((R.id.btnSend));
        btnSend.setOnClickListener(this);
        calendar = Calendar.getInstance();
        updateDateTimeFields();
        // Set up the Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        drawerLayout = findViewById(R.id.drawer_layout_nurse);
        navigationView = findViewById(R.id.nav_view_nurse);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem navReactionItem = navigationView.getMenu().findItem(R.id.nav_reaction);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Handle navigation item clicks here
            if (item.getItemId() == R.id.nav_reaction) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if(item.getItemId() == R.id.nav_personal_reaction) {
                intent = new Intent(this, ChildActivity.class); // Change to your desired activity
                intent.putExtra("REACTION_OBJECT", reaction);
                startActivity(intent);

            }
             else if (item.getItemId() == R.id.nav_reactionList) {
                intent = new Intent(this, ReactionListActivity.class);
                intent.putExtra("REACTION_OBJECT", reaction);
                if(newReactionSend!=null)
                {
                    intent.putExtra("NEWREACTIONSEND_OBJECT", newReactionSend);
                }
                startActivity(intent);
            }
             else if (item.getItemId() == R.id.nav_information) {
                intent = new Intent(this, information.class);
                intent.putExtra("REACTION_OBJECT", reaction);
                startActivity(intent);
            }

            // Close the drawer after handling the click
            drawerLayout.closeDrawer(GravityCompat.START);

            return true; // Return true to indicate the item has been handled
        }

    private void updateDateTimeFields() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        editTextDate.setText(dateFormat.format(calendar.getTime()));
        editTextTime.setText(timeFormat.format(calendar.getTime()));
    }

    public void showDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateTimeFields();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void showTimePickerDialog(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    updateDateTimeFields();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnAdd)
        {
            createLoginDialog();
        }
        if (view==btnSend)
        {
            newReactionSend = new Reaction(reaction.getCode(),editTextDate.getText().toString(),description.getSelectedItem().toString(),
                    editTextTime.getText().toString());
            d.dismiss();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createLoginDialog()
    {
        d.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
