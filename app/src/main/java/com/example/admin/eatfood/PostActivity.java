package com.example.admin.eatfood;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.eatfood.model.Posts;
import com.example.admin.eatfood.model.User;

import java.util.Calendar;

public class PostActivity extends AppCompatActivity {
    private Button btn,submit;

    Spinner spinerCate,sex_limit;
    private int cate_choice;
    private int sex_choice;
    String date;
    private int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        String[] cates = Posts.getCate();
        spinerCate = (Spinner) findViewById(R.id.spinner_cate);
        ArrayAdapter<String> _cates = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cates);
        spinerCate.setAdapter(_cates);
        spinerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "你選的是" + lunch[position], Toast.LENGTH_SHORT).show();
                cate_choice = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final String[] sex = {"不限","男生","女生"};
        sex_limit = (Spinner) findViewById(R.id.sex_limit);
        ArrayAdapter<String> _sex = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sex);
        sex_limit.setAdapter(_sex);
        sex_limit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "你選的是" + lunch[position], Toast.LENGTH_SHORT).show();
                sex_choice = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn = (Button)findViewById(R.id.btn_date);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                date();
                datePickerDialog();
            }
        });

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                create_post();
            }
        });
    }

    private void datePickerDialog() {
        new DatePickerDialog(PostActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date = String.format("%d-%d-%d", year, monthOfYear, dayOfMonth);
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

    private void create_post(){
        Posts pst = new Posts();
        pst.restaurant_name = ((EditText)findViewById(R.id.restaurant_name)).getText().toString();
        pst.restaurant_branch = ((EditText)findViewById(R.id.restaurant_branch)).getText().toString();
        pst.restaurant_address = ((EditText)findViewById(R.id.restaurant_address)).getText().toString();
        pst.people_limit = Integer.parseInt(((EditText)findViewById(R.id.people_limit)).getText().toString());
        pst.meeting_date = date;
        pst.content = ((EditText)findViewById(R.id.content)).getText().toString();
        pst.cate_id = cate_choice;
        pst.sex_limit = sex_choice;
        pst.owner_id = User.getUsr().id;

        if( pst.create()){
            //成功創建
        }


    }

}
