package com.example.ass.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ass.R;
import com.example.ass.dao.Top10DAO;

import java.time.Month;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DTFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DTFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DTFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DTFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DTFragment newInstance(String param1, String param2) {
        DTFragment fragment = new DTFragment();
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
        View view = inflater.inflate(R.layout.fragment_d_t, container, false);
        EditText edBatDau = view.findViewById(R.id.edBatDau);
        EditText edKetThuc = view.findViewById(R.id.edKetThuc);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        Calendar calendar = Calendar.getInstance();
        edBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = "";
                        String thang = "";
                        if(i2<10){
                            ngay = "0"+i2;
                        }else {
                            ngay = String.valueOf(i2);
                        }
                        if((i1+1)<10){
                            thang = "0"+(i1+1);
                        }else {
                            thang = String.valueOf(i1+1);
                        }
                        edBatDau.setText(ngay+"/"+thang+"/"+i);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        edKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = "";
                        String thang = "";
                        if(i2<10){
                            ngay = "0"+i2;
                        }else {
                            ngay = String.valueOf(i2);
                        }
                        if((i1+1)<10){
                            thang = "0"+(i1+1);
                        }else {
                            thang = String.valueOf(i1+1);
                        }
                        edKetThuc.setText(ngay+"/"+thang+"/"+i);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Top10DAO top10DAO = new Top10DAO(getContext());
                int doanhThu = top10DAO.getDoanhThu(edBatDau.getText().toString(),edKetThuc.getText().toString());
                tvDoanhThu.setText(doanhThu+" VNĐ");
            }
        });
        return view;
    }
}