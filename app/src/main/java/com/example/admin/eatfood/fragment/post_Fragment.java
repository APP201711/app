package com.example.admin.eatfood.fragment;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.eatfood.PostActivity;
import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.Posts;
import com.example.admin.eatfood.model.User;

import java.util.Calendar;


public class post_Fragment extends Fragment {

    private Button btn,submit;

    Spinner spinerCate,sex_limit;
    private int cate_choice;
    private int sex_choice;
    String date;
    private int year,month,day;
    View PostView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PostView = inflater.inflate(R.layout.fragment_post, container, false);
        String[] cates = Posts.getCate();
        spinerCate = (Spinner) PostView.findViewById(R.id.spinner_cate);
        ArrayAdapter<String> _cates = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, cates);
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
        sex_limit = (Spinner) PostView.findViewById(R.id.sex_limit);
        ArrayAdapter<String> _sex = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, sex);
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


        btn = (Button)PostView.findViewById(R.id.btn_date);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                date();
                datePickerDialog();
            }
        });

        submit = (Button)PostView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                create_post();
            }
        });

        return PostView;
    }
    private void datePickerDialog() {
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date = String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
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
//        Posts pst = new Posts();
//        pst.restaurant_name = ((EditText) PostView.findViewById(R.id.restaurant_name)).getText().toString();
//        pst.restaurant_branch = ((EditText) PostView.findViewById(R.id.restaurant_branch)).getText().toString();
//        pst.restaurant_address = ((EditText) PostView.findViewById(R.id.restaurant_address)).getText().toString();
//        pst.people_limit = Integer.parseInt(((EditText) PostView.findViewById(R.id.people_limit__)).getText().toString());
//        pst.meeting_date = date;
//        pst.content = ((EditText) PostView.findViewById(R.id.content)).getText().toString();
//        pst.cate_id = cate_choice;
//        pst.sex_limit = sex_choice;
//        pst.owner_id = User.getUsr().id;
        Log.e("date",date);
//
//        if( pst.create()){
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.contendor,new Home_Fragment()).commit();
//        }
    }


}


