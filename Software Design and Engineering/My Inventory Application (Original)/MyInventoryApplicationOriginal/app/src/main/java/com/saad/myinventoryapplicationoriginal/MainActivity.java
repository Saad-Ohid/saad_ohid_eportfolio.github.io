package com.saad.myinventoryapplicationoriginal;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new InventoryDbHelper(this);
        nameInput = findViewById(R.id.edit_item_name);
        qtyInput = findViewById(R.id.edit_item_qty);
        Button addButton = findViewById(R.id.btn_add);

        RecyclerView recyclerView = findViewById(R.id.inventory_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new InventoryAdapter(dbHelper.getAllItems());
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String qtyStr = qtyInput.getText().toString().trim();

            if (!name.isEmpty() && !qtyStr.isEmpty()) {
                // Original logic: crashable if not a number
                int qty = Integer.parseInt(qtyStr);
                dbHelper.addItem(name, qty);
                adapter.swapCursor(dbHelper.getAllItems());

                nameInput.setText("");
                qtyInput.setText("");
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}