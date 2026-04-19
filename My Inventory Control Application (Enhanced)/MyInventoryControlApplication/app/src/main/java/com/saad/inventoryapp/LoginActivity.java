package com.saad.inventoryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Initialize default credentials if they don't exist
        if (!prefs.contains("user")) {
            prefs.edit().putString("user", "admin").putString("pass", "password123").apply();
        }

        EditText userField = findViewById(R.id.edit_username);
        EditText passField = findViewById(R.id.edit_password);
        Button loginBtn = findViewById(R.id.btn_login);
        Button updateBtn = findViewById(R.id.btn_update_creds);

        // 1. SECURE LOGIN
        loginBtn.setOnClickListener(v -> {
            String enteredUser = userField.getText().toString().trim();
            String enteredPass = passField.getText().toString().trim();

            String savedUser = prefs.getString("user", "admin");
            String savedPass = prefs.getString("pass", "password123");

            if (enteredUser.equals(savedUser) && enteredPass.equals(savedPass)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. IDENTITY VERIFICATION & UPDATE
        updateBtn.setOnClickListener(v -> {
            String inputUser = userField.getText().toString().trim();
            String inputPass = passField.getText().toString().trim();

            String savedUser = prefs.getString("user", "admin");
            String savedPass = prefs.getString("pass", "password123");

            // CHECK: Does the person trying to update actually know the current password?
            if (inputUser.equals(savedUser) && inputPass.equals(savedPass)) {

                // If verified, we can now allow a change.
                // For this UI, we will use a "Toast" to guide the user.
                Toast.makeText(this, "Verified! Now type your NEW username/password and click this button again.", Toast.LENGTH_LONG).show();

                // This boolean "flag" helps the button know the NEXT click is for saving
                updateBtn.setText("Save New Credentials");
                updateBtn.setOnClickListener(v2 -> {
                    String newUser = userField.getText().toString().trim();
                    String newPass = passField.getText().toString().trim();

                    if (!newUser.isEmpty() && !newPass.isEmpty()) {
                        prefs.edit().putString("user", newUser).putString("pass", newPass).apply();
                        Toast.makeText(this, "Security Credentials Updated!", Toast.LENGTH_SHORT).show();

                        // Reset button for next time
                        updateBtn.setText("Verify & Change Credentials");
                        recreate(); // Refresh activity
                    }
                });

            } else {
                // SECURITY ALERT: Block unauthorized change attempts
                Toast.makeText(this, "Denied: Current credentials required for modification.", Toast.LENGTH_LONG).show();
            }
        });
    }
}