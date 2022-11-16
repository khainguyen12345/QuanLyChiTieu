package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class tienChi extends AppCompatActivity {
    Button buttonChi ,nhapKhoanchi , buttonThu ;
    ImageButton enterButton , calendarButton , reportButton , otherButton;
    TextView ngay , ghichu , tienchi;
    myDB myDB;
    Calendar calendar = Calendar.getInstance();
    public int DAY = calendar.get(Calendar.DATE);
    public int MONTH = calendar.get(Calendar.MONTH);
    public int YEAR = calendar.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_chi);
        findView();
        nhapKhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        choose();
                    }
                });
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
        myDB = new myDB(this);
    }
}