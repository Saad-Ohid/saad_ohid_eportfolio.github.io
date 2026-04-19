package com.saad.myinventoryapplicationoriginal;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {
    private Cursor cursor;

    public InventoryAdapter(Cursor cursor) { this.cursor = cursor; }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new InventoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            holder.nameText.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            holder.qtyText.setText("Qty: " + cursor.getInt(cursor.getColumnIndexOrThrow("qty")));
        }
    }

    @Override
    public int getItemCount() { return (cursor == null) ? 0 : cursor.getCount(); }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        notifyDataSetChanged();
    }

    static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, qtyText;
        public InventoryViewHolder(@NonNull View v) {
            super(v);
            nameText = v.findViewById(R.id.display_name);
            qtyText = v.findViewById(R.id.display_qty);
        }
    }
}