package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlychitieu.DanhMucChiTieu.DanhMucChiTieu;
import com.example.quanlychitieu.DanhMucChiTieu.DanhMucChiTieuAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class baoCao extends AppCompatActivity {
    public static DatePickerDialog datePickerDialog;
    private PreferenceManager preferenceManager;
    private RecyclerView rcv_danhmucchitieu;
    private DanhMucChiTieuAdapter danhmucchitieuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao);
        preferenceManager = new PreferenceManager(getApplicationContext());
        Button btnThuNhap = (Button) findViewById(R.id.buttonThuNhap);

        rcv_danhmucchitieu = findViewById(R.id.rcv_danhmucchitieu);
        danhmucchitieuAdapter = new DanhMucChiTieuAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_danhmucchitieu.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_danhmucchitieu.addItemDecoration(itemDecoration);

        List<DanhMucChiTieu> danhMucChiTieus = new ArrayList<>();
        Cursor cursor = MainActivity.mydb.getData("select * from thuchis where userId = "+preferenceManager.getString("userId"));
        while (cursor.moveToNext()){
            danhMucChiTieus.add(new DanhMucChiTieu(cursor.getInt(0), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
        }

        danhmucchitieuAdapter.setData(danhMucChiTieus);
        rcv_danhmucchitieu.setAdapter(danhmucchitieuAdapter);

        ImageButton btnNhap = (ImageButton) findViewById(R.id.imageButtonNhap_baocao_chi);
        ImageButton btnLich = (ImageButton) findViewById(R.id.imageButtonLich_baocao_chi);
        ImageButton btnBaoCao = (ImageButton) findViewById(R.id.imageButtonBaoCao_baocao_chi);
        ImageButton btnKhac = (ImageButton) findViewById(R.id.imageButtonKhac_baocao_chi);

        TextView tvDatePick = (TextView) findViewById(R.id.viewYearPicker_Chi);
        DatePickerDialog.OnDateSetListener setListener;

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = String.valueOf(year);
                tvDatePick.setText(date);
            }
        };

        tvDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(
                        baoCao.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                datePickerDialog.show();
            }
        });

        // năm từ báo cao thu nhập
        Intent intent = getIntent();
        String getYear = intent.getStringExtra("year_thu");
        tvDatePick.setText(getYear);

//        btnThuNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(baoCao.this,BaoCaoThuNhap.class);
//                String getYear = tvDatePick.getText().toString();
//                intent.putExtra("year", getYear);
//                startActivity(intent);
//            }
//        });

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(baoCao.this, tienChi.class);
                startActivity(intent);
            }
        });

        btnBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(baoCao.this, baoCao.class);
                startActivity(intent);
            }
        });

        btnKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(baoCao.this, khac.class);
                startActivity(intent);
            }
        });
    }
}