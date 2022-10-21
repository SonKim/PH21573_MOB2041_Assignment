package com.example.ass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass.R;
import com.example.ass.model.Sach;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {
    Context context;
    ArrayList<Sach> saches;

    public Top10Adapter(Context context, ArrayList<Sach> saches) {
        this.context = context;
        this.saches = saches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_top10,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaSach.setText("Mã sách: "+saches.get(position).getMaSach());
        holder.tvTenSach.setText("Tên sách: "+saches.get(position).getTenSach());
        holder.tvSoLuongMuon.setText("Số lượng đã mượn: "+saches.get(position).getSoLuongMuon());
    }

    @Override
    public int getItemCount() {
        return saches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaSach,tvTenSach,tvSoLuongMuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvSoLuongMuon = itemView.findViewById(R.id.tvSoLuongMuon);
        }
    }
}
