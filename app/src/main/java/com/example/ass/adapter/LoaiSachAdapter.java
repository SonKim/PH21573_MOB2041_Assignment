package com.example.ass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass.R;
import com.example.ass.dao.LoaiSachDAO;
import com.example.ass.model.LoaiSach;
import com.example.ass.model.SuaLoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    Context context;
    ArrayList<LoaiSach> loaiSaches;
    SuaLoaiSach suaLoaiSach;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> loaiSaches, SuaLoaiSach suaLoaiSach) {
        this.context = context;
        this.loaiSaches = loaiSaches;
        this.suaLoaiSach = suaLoaiSach;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaLoaiSach.setText("Mã loại: "+loaiSaches.get(position).getMaLoai());
        holder.tvTenLoaiSach.setText("Tên loại: "+loaiSaches.get(position).getTenLoai());
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                boolean check = loaiSachDAO.xoa(loaiSaches.get(holder.getAdapterPosition()).getMaLoai());
                if(check){
                    loaiSaches.clear();
                    loaiSaches = loaiSachDAO.getAllLoaiSach();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach = loaiSaches.get(holder.getAdapterPosition());
                suaLoaiSach.onClick(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiSaches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLoaiSach,tvTenLoaiSach;
        ImageView ivXoa,ivSua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLoaiSach = itemView.findViewById(R.id.tvMaLoaiSach);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            ivXoa = itemView.findViewById(R.id.ivXoa);
            ivSua = itemView.findViewById(R.id.ivSua);
        }
    }
}
