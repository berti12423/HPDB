package com.example.hospitalproject;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ADMIN_PASSWORD = "admin123";

    private EditText editTextUsername, etStuffPassword;
    private TextView tvAdditionalInfo, usernameError;
    private Button buttonLogin, btnDialogContinue;
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager.LoadDataBase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, DataChangeService.class);
        startService(intent);

        usernameError = findViewById(R.id.usernameError);
        editTextUsername = findViewById(R.id.editTextUsername);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = editTextUsername.getText().toString();

        if (v == buttonLogin) {
            handleLogin(username);
        } else if (v == btnDialogContinue) {
            handleDialogContinue(username);
        }
    }

    private void handleLogin(String username) {
        usernameError.setVisibility(View.GONE);

        if (username.length() < 4) {
            usernameError.setVisibility(View.VISIBLE);
        } else {
            if (username.endsWith("3") || username.endsWith("4")) {
                createLoginDialog();
            } else if (username.endsWith("2") || username.endsWith("1")) {
                navigateToChildActivity(username);
            }
        }
    }

    private void handleDialogContinue(String username) {
        if (etStuffPassword.getText().toString().equals(ADMIN_PASSWORD)) {
            navigateToNurseMainPage(username);
        } else {
            tvAdditionalInfo.setText("Wrong password");
        }
    }

    private void createLoginDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.password_dialog);
        d.setTitle("Stuff password");
        d.setCancelable(true);

        etStuffPassword = d.findViewById(R.id.etStuffPassword);
        tvAdditionalInfo = d.findViewById(R.id.tvWrongPass);
        btnDialogContinue = d.findViewById(R.id.btnDialogContinue);
        btnDialogContinue.setOnClickListener(this);

        d.show();
    }

    private void navigateToChildActivity(String username) {
        Intent intent = new Intent(MainActivity.this, ChildActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    private void navigateToNurseMainPage(String username) {
        Intent intent = new Intent(MainActivity.this, nurseMainPage.class);
        Patient patient = new Patient(username);
        intent.putExtra("PATIENT", patient);
        startActivity(intent);
    }
}
