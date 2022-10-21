package com.example.ass.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass.R;
import com.example.ass.adapter.LoaiSachAdapter;
import com.example.ass.adapter.Top10Adapter;
import com.example.ass.dao.LoaiSachDAO;
import com.example.ass.dao.Top10DAO;
import com.example.ass.model.LoaiSach;
import com.example.ass.model.SuaLoaiSach;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QLLSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QLLSFragment extends Fragment {
    RecyclerView rvLoaiSach;
    LoaiSachAdapter loaiSachAdapter;
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> loaiSachs;
    EditText edLoaiSach;
    int maLoai = -1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QLLSFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QLLSFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QLLSFragment newInstance(String param1, String param2) {
        QLLSFragment fragment = new QLLSFragment();
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
        View view = inflater.inflate(R.layout.fragment_q_l_l_s, container, false);
        edLoaiSach = view.findViewById(R.id.edLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);
        rvLoaiSach = view.findViewById(R.id.rvLoaiSach);
        refresh();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edLoaiSach.getText().toString();
                if(loaiSachDAO.them(tenLoai)){
                    Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                    refresh();
                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edLoaiSach.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maLoai,tenLoai);
                if(maLoai == -1){
                    Toast.makeText(getContext(), "Vui lòng chọn loại sách muốn sửa", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(loaiSachDAO.sua(loaiSach)){
                    refresh();
                    edLoaiSach.setText("");
                    maLoai = -1;
                }else {
                    Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void refresh(){
        loaiSachDAO = new LoaiSachDAO(getContext());
        loaiSachs = loaiSachDAO.getAllLoaiSach();
        loaiSachAdapter = new LoaiSachAdapter(getContext(), loaiSachs, new SuaLoaiSach() {
            @Override
            public void onClick(LoaiSach loaiSach) {
                edLoaiSach.setText(loaiSach.getTenLoai());
                maLoai = loaiSach.getMaLoai();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvLoaiSach.setHasFixedSize(true);
        rvLoaiSach.setLayoutManager(linearLayoutManager);
        rvLoaiSach.setAdapter(loaiSachAdapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
}