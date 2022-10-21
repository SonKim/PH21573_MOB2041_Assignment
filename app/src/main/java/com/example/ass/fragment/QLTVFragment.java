package com.example.ass.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass.R;
import com.example.ass.adapter.ThanhVienAdapter;
import com.example.ass.dao.ThanhVienDAO;
import com.example.ass.model.LoaiSach;
import com.example.ass.model.SuaThanhVien;
import com.example.ass.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QLTVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QLTVFragment extends Fragment {
    RecyclerView rvThanhVien;
    ArrayList<ThanhVien> thanhViens;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVienDAO thanhVienDAO;
    EditText edTenThanhVien,edNamSinh;
    int matv = -1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QLTVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QLTVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QLTVFragment newInstance(String param1, String param2) {
        QLTVFragment fragment = new QLTVFragment();
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
        View view = inflater.inflate(R.layout.fragment_q_l_t_v, container, false);
        rvThanhVien = view.findViewById(R.id.rvThanhVien);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);
        edTenThanhVien = view.findViewById(R.id.edTenThanhVien);
        edNamSinh = view.findViewById(R.id.edNamSinh);
        refresh();
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenThanhVien = edTenThanhVien.getText().toString();
                String namSinh = edNamSinh.getText().toString();
                ThanhVien thanhVien = new ThanhVien(matv,tenThanhVien,namSinh);
                if(matv==-1){
                    Toast.makeText(getContext(), "Vui lòng chọn thành viên cần sửa", Toast.LENGTH_SHORT).show();
                }
                if(thanhVienDAO.sua(thanhVien)){
                    Toast.makeText(getContext(), "Sửa thành viên thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Sửa thành viên thất bại", Toast.LENGTH_SHORT).show();
                }
                matv = -1;
                refresh();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenThanhVien = edTenThanhVien.getText().toString();
                String namSinh = edNamSinh.getText().toString();
                ThanhVien thanhVien = new ThanhVien(tenThanhVien,namSinh);
                if(thanhVienDAO.them(thanhVien)){
                    Toast.makeText(getContext(), "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                    refresh();
                }else {
                    Toast.makeText(getContext(), "Thêm thành viên thất bại", Toast.LENGTH_SHORT).show();
                }
                refresh();
            }
        });
        return view;
    }
    public void refresh(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvThanhVien.setLayoutManager(linearLayoutManager);
        rvThanhVien.setHasFixedSize(true);
        thanhVienDAO = new ThanhVienDAO(getContext());
        thanhViens = thanhVienDAO.getAllThanhVien();
        thanhVienAdapter = new ThanhVienAdapter(getContext(), thanhViens, new SuaThanhVien() {
            @Override
            public void onClick(ThanhVien thanhVien) {
                edTenThanhVien.setText(thanhVien.getTenThanhVien());
                edNamSinh.setText(thanhVien.getNamSinh());
                matv = thanhVien.getMaThanhVien();
            }
        });
        edTenThanhVien.setText("");
        edNamSinh.setText("");
        rvThanhVien.setAdapter(thanhVienAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
}