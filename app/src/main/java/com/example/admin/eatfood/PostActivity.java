package com.example.admin.eatfood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class PostActivity extends AppCompatActivity {
    private Button btn;
    private int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btn = (Button)findViewById(R.id.btn_date);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                date();
                datePickerDialog();
            }
        });
    }

    private void datePickerDialog() {
        new DatePickerDialog(PostActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = String.format("%d-%d-%d", year, monthOfYear, dayOfMonth);
                btn.setText(date);
            }
        }, year, month, day).show();
    }

    private void date() {
        Calendar c = Calendar.getInstance();
        //年
        year = c.get(Calendar.YEAR);
        //月
        month = c.get(Calendar.MONTH);
        //日
        day = c.get(Calendar.DAY_OF_MONTH);

    }



}
