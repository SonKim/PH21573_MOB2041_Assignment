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
import com.example.ass.dao.ThanhVienDAO;
import com.example.ass.model.SuaThanhVien;
import com.example.ass.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    Context context;
    ArrayList<ThanhVien> thanhViens;
    SuaThanhVien suaThanhVien;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> thanhViens, SuaThanhVien suaThanhVien) {
        this.context = context;
        this.thanhViens = thanhViens;
        this.suaThanhVien = suaThanhVien;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_thanhvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaThanhVien.setText("Mã thành viên: "+thanhViens.get(position).getMaThanhVien());
        holder.tvTenThanhVien.setText("Tên thành viên: "+thanhViens.get(position).getTenThanhVien());
        holder.tvNamSinh.setText("Năm sinh: "+thanhViens.get(position).getNamSinh());
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
                ThanhVien thanhVien = thanhViens.get(holder.getAdapterPosition());
                boolean check = thanhVienDAO.xoa(thanhVien);
                if(check){
                    thanhViens.clear();
                    thanhViens = thanhVienDAO.getAllThanhVien();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Xóa thành viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien thanhVien = thanhViens.get(holder.getAdapterPosition());
                suaThanhVien.onClick(thanhVien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thanhViens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaThanhVien,tvTenThanhVien,tvNamSinh;
        ImageView ivXoa,ivSua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaThanhVien = itemView.findViewById(R.id.tvMaThanhVien);
            tvTenThanhVien = itemView.findViewById(R.id.tvTenThanhVien);
            tvNamSinh = itemView.findViewById(R.id.tvNamSinh);
            ivXoa = itemView.findViewById(R.id.ivXoa);
            ivSua = itemView.findViewById(R.id.ivSua);
        }
    }
}
