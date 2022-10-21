package com.example.ass.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass.R;
import com.example.ass.dao.PhieuMuonDAO;
import com.example.ass.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhieuMuon> phieuMuons;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> phieuMuons) {
        this.context = context;
        this.phieuMuons = phieuMuons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_phieumuon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMPM.setText("Mã phiếu mượn: "+phieuMuons.get(position).getMaPhieuMuon());
        holder.tvMTT.setText("Mã thủ thư: "+phieuMuons.get(position).getMaThuThu());
        holder.tvMTV.setText("Mã thành viên: "+phieuMuons.get(position).getMaThanhVien());
        holder.tvMS.setText("Mã sách: "+phieuMuons.get(position).getMaSach());
        holder.tvNgay.setText("Ngày: "+phieuMuons.get(position).getNgay());
        String traSach = "";
        if(phieuMuons.get(position).getTraSach()==1){
            traSach = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }else {
            traSach = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.tvTS.setText("Trả sách: "+traSach);
        holder.tvTT.setText("Tiền thuê: "+phieuMuons.get(position).getTienThue());
        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemTra = phieuMuonDAO.thayDoiTraSach(phieuMuons.get(holder.getAdapterPosition()).getMaPhieuMuon());
                if(kiemTra==true){
                    phieuMuons.clear();
                    phieuMuons = phieuMuonDAO.getAllPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trả sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phieuMuons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMPM,tvMTT,tvMTV,tvMS,tvNgay,tvTS,tvTT;
        Button btnTraSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMPM = itemView.findViewById(R.id.tvMPM);
            tvMTT = itemView.findViewById(R.id.tvMTT);
            tvMTV = itemView.findViewById(R.id.tvMTV);
            tvMS = itemView.findViewById(R.id.tvMS);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTS = itemView.findViewById(R.id.tvTS);
            tvTT = itemView.findViewById(R.id.tvTT);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);
        }
    }
}
