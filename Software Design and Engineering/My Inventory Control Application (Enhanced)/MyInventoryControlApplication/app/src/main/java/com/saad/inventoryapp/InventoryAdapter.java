package com.saad.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {
    private Cursor cursor;
    private Context context;
    private InventoryDbHelper dbHelper;

    public InventoryAdapter(Context context, Cursor cursor, InventoryDbHelper dbHelper) {
        this.context = context;
        this.cursor = cursor;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int qty = cursor.getInt(cursor.getColumnIndexOrThrow("qty"));

            holder.nameText.setText(name);
            holder.qtyText.setText("Qty: " + qty);

            holder.deleteBtn.setOnClickListener(v -> {
                dbHelper.deleteItem(id);
                swapCursor(dbHelper.getAllItems());
                Toast.makeText(context, "Removed: " + name, Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return (cursor == null) ? 0 : cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        notifyDataSetChanged();
    }

    static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, qtyText;
        ImageButton deleteBtn;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.display_name);
            qtyText = itemView.findViewById(R.id.display_qty);
            deleteBtn = itemView.findViewById(R.id.btn_delete);
        }
    }
}