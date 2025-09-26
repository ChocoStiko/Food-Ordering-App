package com.example.angcanan_food_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<foods> items;
    private OnTotalChangedListener listener;
    public double total = 0;

    public interface OnTotalChangedListener {
        void onTotalChanged(double total);
    }

    public Adapter(List<foods> items, OnTotalChangedListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        foods item = items.get(position);

        holder.imgItem.setImageResource(item.getImage());
        holder.txtName.setText(item.getName());
        holder.txtQuantity.setText("x" + String.valueOf(item.getQuantity()));
        holder.txtPrice.setText("₱" + String.format(Locale.US, "%.2f", item.getPrice()));

        holder.addButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            updateTotal();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateTotal() {
        total = 0;
        for (foods f : items) {
            total += f.getPrice() * f.getQuantity();
        }
        if (listener != null) {
            listener.onTotalChanged(total);
        }
    }

    public void clearButton() {
        for (foods f : items) {
            f.setQuantity(0);
        }
        total = 0;
        notifyDataSetChanged();
        if (listener != null) {
            listener.onTotalChanged(total);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView txtName, txtPrice, txtQuantity;
        ImageButton addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imageview);
            txtName = itemView.findViewById(R.id.name);
            txtPrice = itemView.findViewById(R.id.price);
            txtQuantity = itemView.findViewById(R.id.quantity);
            addButton = itemView.findViewById(R.id.addbutton);
        }
    }
}
