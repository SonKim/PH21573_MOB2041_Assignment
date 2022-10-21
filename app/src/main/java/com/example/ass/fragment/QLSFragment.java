package com.example.ass.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.ass.adapter.SachAdapter;
import com.example.ass.dao.LoaiSachDAO;
import com.example.ass.dao.SachDAO;
import com.example.ass.model.LoaiSach;
import com.example.ass.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QLSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QLSFragment extends Fragment {
    RecyclerView rvSach;
    ArrayList<Sach> saches;
    SachDAO sachDAO;
    SachAdapter sachAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QLSFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QLSFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QLSFragment newInstance(String param1, String param2) {
        QLSFragment fragment = new QLSFragment();
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
        View view = inflater.inflate(R.layout.fragment_q_l_s, container, false);
        FloatingActionButton btnThem = view.findViewById(R.id.btnThem);
        rvSach = view.findViewById(R.id.rvSach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvSach.setLayoutManager(linearLayoutManager);
        rvSach.setHasFixedSize(true);
        sachDAO = new SachDAO(getContext());
        saches = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(getContext(),saches,getDSLoaiSach());
        rvSach.setAdapter(sachAdapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.them_sach_dialog,null);
                builder.setView(view1);
                EditText edTenSach = view1.findViewById(R.id.edTenSach);
                EditText edGiaSach = view1.findViewById(R.id.edGiaSach);
                Spinner spnLoaiSach = view1.findViewById(R.id.spnLoaiSach);
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),getDSLoaiSach(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,new String[]{"TENLOAI"},new int[]{android.R.id.text1});
                spnLoaiSach.setAdapter(simpleAdapter);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tenSach = edTenSach.getText().toString();
                        int giaSach = Integer.parseInt(edGiaSach.getText().toString());
                        HashMap<String,Object> hashMap = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                        int maLoai = (int) hashMap.get("MALOAI");
                        String tenLoai = (String) hashMap.get("TENLOAI");
                        Sach sach = new Sach(tenSach,giaSach,maLoai,tenLoai);
                        boolean check = sachDAO.them(sach);
                        if(check){
                            refresh();
                            Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
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
        return view;
    }
    public void refresh(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvSach.setLayoutManager(linearLayoutManager);
        rvSach.setHasFixedSize(true);
        sachDAO = new SachDAO(getContext());
        saches = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(getContext(),saches,getDSLoaiSach());
        rvSach.setAdapter(sachAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
    public ArrayList<HashMap<String,Object>> getDSLoaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> loaiSaches = loaiSachDAO.getAllLoaiSach();
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(LoaiSach loaiSach : loaiSaches){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("MALOAI",loaiSach.getMaLoai());
            hashMap.put("TENLOAI",loaiSach.getTenLoai());
            list.add(hashMap);
        }
        return list;
    }
}