package com.example.ass.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ass.R;
import com.example.ass.adapter.PhieuMuonAdapter;
import com.example.ass.dao.PhieuMuonDAO;
import com.example.ass.dao.SachDAO;
import com.example.ass.dao.ThanhVienDAO;
import com.example.ass.model.PhieuMuon;
import com.example.ass.model.Sach;
import com.example.ass.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QLPMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QLPMFragment extends Fragment {
    RecyclerView rvQuanLyPhieuMuon;
    FloatingActionButton btnThem;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> phieuMuons;
    PhieuMuonAdapter phieuMuonAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QLPMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QLPMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QLPMFragment newInstance(String param1, String param2) {
        QLPMFragment fragment = new QLPMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_p_m, container, false);
        rvQuanLyPhieuMuon = view.findViewById(R.id.rvQuanLyPhieuMuon);
        btnThem = view.findViewById(R.id.btnThem);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        phieuMuons = phieuMuonDAO.getAllPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvQuanLyPhieuMuon.setHasFixedSize(true);
        rvQuanLyPhieuMuon.setLayoutManager(linearLayoutManager);
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(),phieuMuons);
        rvQuanLyPhieuMuon.setAdapter(phieuMuonAdapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.them_phieu_muon_dialog,null);
                builder.setView(view1);
                Spinner spnThanhVien = view1.findViewById(R.id.spnThanhVien);
                getDataThanhVien(spnThanhVien);
                Spinner spnSach = view1.findViewById(R.id.spnSach);
                getDataSach(spnSach);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HashMap<String, Object> hashMap = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                        int matv = (int) hashMap.get("MATV");
                        HashMap<String, Object> hashMap1 = (HashMap<String, Object>) spnSach.getSelectedItem();
                        int mas = (int) hashMap1.get("MASACH");
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("TAIKHOAN", Context.MODE_PRIVATE);
                        String mtt = sharedPreferences.getString("matt","");
                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd//MM/yyyy", Locale.getDefault());
                        String ngay = simpleDateFormat.format(currentTime);
                        int giaSach = (int) hashMap1.get("GIASACH");
                        PhieuMuon phieuMuon = new PhieuMuon(mtt,matv,mas,ngay,0,giaSach);
                        boolean kiemTra = phieuMuonDAO.them(phieuMuon);
                        if(kiemTra){
                            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
                        }
                        refresh();
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
        return view;
    }
    public void getDataThanhVien(Spinner spinner){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> thanhViens = thanhVienDAO.getAllThanhVien();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for(ThanhVien tv : thanhViens){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("MATV",tv.getMaThanhVien());
            list.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),list, com.bumptech.glide.R.layout.support_simple_spinner_dropdown_item,new String[]{"MATV"},new int[]{android.R.id.text1});
        spinner.setAdapter(simpleAdapter);
    }
    public void getDataSach(Spinner spinner){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> sachs = sachDAO.getAllSach();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for(Sach sach : sachs){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("MASACH",sach.getMaSach());
            hashMap.put("GIASACH",sach.getGiaThue());
            list.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),list, com.bumptech.glide.R.layout.support_simple_spinner_dropdown_item,new String[]{"MASACH"},new int[]{android.R.id.text1});
        spinner.setAdapter(simpleAdapter);
    }
    public void refresh(){
        phieuMuons = phieuMuonDAO.getAllPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvQuanLyPhieuMuon.setHasFixedSize(true);
        rvQuanLyPhieuMuon.setLayoutManager(linearLayoutManager);
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(),phieuMuons);
        rvQuanLyPhieuMuon.setAdapter(phieuMuonAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
}