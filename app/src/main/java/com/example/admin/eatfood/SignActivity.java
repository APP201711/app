package com.example.admin.eatfood;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.admin.eatfood.model.User;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Button btn_register = (Button) findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    private void register() {
        String Username = ((EditText) findViewById(R.id.Username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String chk_password = ((EditText) findViewById(R.id.chk_password)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String address = ((EditText) findViewById(R.id.address)).getText().toString();
        RadioGroup r = (RadioGroup) findViewById(R.id.sex);
        int selectedId = r.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String sex = radioButton.getText().toString();
        if(sex.equals("男")){
            sex = "0";
        }else{
            sex = "1";
        }
        Boolean reg = User.register(Username, password, address, phone, sex);
        if(reg){
            finish();
        }else{
            Toast.makeText(SignActivity.this,"帳號有人使用", Toast.LENGTH_SHORT).show();
            Log.e("RegisterError", "帳號有人使用");
        }
    }
}


