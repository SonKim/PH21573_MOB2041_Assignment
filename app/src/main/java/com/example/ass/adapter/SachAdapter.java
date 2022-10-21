package com.example.ass.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass.R;
import com.example.ass.dao.SachDAO;
import com.example.ass.model.Sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    Context context;
    ArrayList<Sach> saches;
    ArrayList<HashMap<String,Object>> list;

    public SachAdapter(Context context, ArrayList<Sach> saches, ArrayList<HashMap<String, Object>> list) {
        this.context = context;
        this.saches = saches;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaSach.setText("Mã sách: "+saches.get(position).getMaSach());
        holder.tvTenSach.setText("Tên sách: "+saches.get(position).getTenSach());
        holder.tvGiaThue.setText("Giá thuê: "+saches.get(position).getGiaThue());
        holder.tvMaLoai.setText("Mã loại: "+saches.get(position).getMaLoai());
        holder.tvTenLoai.setText("Tên loại: "+saches.get(position).getTenLoai());
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int masach = saches.get(holder.getAdapterPosition()).getMaSach();
                SachDAO sachDAO = new SachDAO(context);
                boolean check = sachDAO.xoa(masach);
                if(check){
                    saches.clear();
                    saches = sachDAO.getAllSach();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa sách thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.sua_sach_dialog,null);
                builder.setView(view1);
                EditText edTenSach = view1.findViewById(R.id.edTenSach);
                EditText edGiaSach = view1.findViewById(R.id.edGiaSach);
                Spinner spnLoaiSach = view1.findViewById(R.id.spnLoaiSach);
                SimpleAdapter simpleAdapter = new SimpleAdapter(context,list, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,new String[]{"TENLOAI"},new int[]{android.R.id.text1});
                spnLoaiSach.setAdapter(simpleAdapter);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tenSach = edTenSach.getText().toString();
                        int giaSach = Integer.parseInt(edGiaSach.getText().toString());
                        HashMap<String,Object> hashMap = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                        int maLoai = (int) hashMap.get("MALOAI");
                        String tenLoai = (String) hashMap.get("TENLOAI");
                        Sach sach = saches.get(holder.getAdapterPosition());
                        sach.setTenSach(tenSach);
                        sach.setGiaThue(giaSach);
                        sach.setMaLoai(maLoai);
                        sach.setTenLoai(tenLoai);
                        SachDAO sachDAO = new SachDAO(context);
                        boolean check = sachDAO.sua(sach);
                        if(check){
                            saches.clear();
                            saches = sachDAO.getAllSach();
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sửa sách thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return saches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaSach,tvTenSach,tvGiaThue,tvMaLoai,tvTenLoai;
        ImageView ivSua,ivXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            ivSua = itemView.findViewById(R.id.ivSua);
            ivXoa = itemView.findViewById(R.id.ivXoa);
        }
    }
}
