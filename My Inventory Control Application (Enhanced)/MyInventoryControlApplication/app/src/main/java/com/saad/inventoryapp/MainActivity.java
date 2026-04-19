package com.saad.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private InventoryDbHelper dbHelper;
    private EditText nameInput, qtyInput;
    private InventoryAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize Database and UI Elements
        dbHelper = new InventoryDbHelper(this);
        nameInput = findViewById(R.id.edit_item_name);
        qtyInput = findViewById(R.id.edit_item_qty);
        Button addButton = findViewById(R.id.btn_add);
        Button logoutButton = findViewById(R.id.btn_logout); // New Logout Button

        // 2. Setup the List (RecyclerView)
        recyclerView = findViewById(R.id.inventory_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. Connect the Adapter (Bridge between DB and Screen)
        adapter = new InventoryAdapter(this, dbHelper.getAllItems(), dbHelper);
        recyclerView.setAdapter(adapter);

        // 4. Logic for Adding an Item (Create)
        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String qtyStr = qtyInput.getText().toString().trim();

            if (!name.isEmpty() && !qtyStr.isEmpty()) {
                try {
                    // Add to SQLite Database
                    dbHelper.addItem(name, Integer.parseInt(qtyStr));

                    // Refresh the list immediately
                    adapter.swapCursor(dbHelper.getAllItems());

                    Toast.makeText(this, "Saved: " + name, Toast.LENGTH_SHORT).show();

                    // Reset fields for the next entry
                    nameInput.setText("");
                    qtyInput.setText("");
                    nameInput.requestFocus();

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Quantity must be a number", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // 5. SECURITY ENHANCEMENT: Logout Logic
        logoutButton.setOnClickListener(v -> {
            // Create intent to go back to Login
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            // This is the "Security Mindset" part:
            // It clears the history so the user can't click 'back' to see the data.
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Always refresh data when the user comes back to the app
        if (adapter != null && dbHelper != null) {
            adapter.swapCursor(dbHelper.getAllItems());
        }
    }
}