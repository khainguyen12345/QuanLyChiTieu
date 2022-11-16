package com.example.quanlychitieu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Money_earned extends AppCompatActivity {
    Button buttonChi ,nhapKhoanchi , buttonThu ;
    ImageButton enterButton , calendarButton , reportButton , otherButton;
    TextView ngay , ghichu , tienchi;
    private PreferenceManager preferenceManager;

    Calendar calendar = Calendar.getInstance();
    public int DAY = calendar.get(Calendar.DATE);
    public int MONTH = calendar.get(Calendar.MONTH);
    public int YEAR = calendar.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_earned);
        findView();
        preferenceManager = new PreferenceManager(getApplicationContext());
        buttonThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , tienChi.class);
                startActivity(intent);
            }
        });
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext() , baoCao.class);
                    startActivity(intent);
            }
        });
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.quanlychitieu.khac.class);
                startActivity(intent);
            }
        });
        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose();
            }
        });
        nhapKhoanchi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String getNgay = ngay.getText().toString();
                String getGhichu = ghichu.getText().toString();
                String getTienchi = tienchi.getText().toString();
                if(getGhichu.isEmpty() || getTienchi.isEmpty()){
                    Toast.makeText(Money_earned.this, "Please enter all", Toast.LENGTH_SHORT).show();
                }else{
                    int tien = Integer.parseInt(getTienchi);
                    Boolean insertData = MainActivity.mydb.insertThuChis(getNgay,getGhichu , tien, 0, preferenceManager.getString("userId"));
                    if(insertData == true) {
                        Toast.makeText(Money_earned.this, "Insert Succesfull", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Money_earned.this, "Insert failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void choose() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                ngay.setText(simpleDateFormat.format(calendar.getTime()));

            }
        },YEAR , MONTH, DAY);
        datePickerDialog.show();
    }
    private void findView() {
        buttonThu = (Button) findViewById(R.id.btn_tienthu);
        buttonChi = (Button) findViewById(R.id.btn_tienchi);
        nhapKhoanchi = (Button) findViewById(R.id.nhapkhoanchi);
        calendarButton = (ImageButton) findViewById(R.id.imageButtonLich_chi);
        reportButton = (ImageButton) findViewById(R.id.imageButtonBaoCao_chi);
        otherButton = (ImageButton) findViewById(R.id.imageButtonKhac_chi);
        ngay = (TextView) findViewById(R.id.datePick_tienchi);
        ghichu = (TextView) findViewById(R.id.nhapghichu);
        tienchi = (TextView) findViewById(R.id.nhaptienchi);
    }
}