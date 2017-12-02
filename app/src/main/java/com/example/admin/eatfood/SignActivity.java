package com.example.admin.eatfood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    protected String email;
    protected String password;
    protected String phone;
    protected String sex;
    protected String address;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        auth = FirebaseAuth.getInstance();
        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    private void register() {
        this.email = ((EditText)findViewById(R.id.email)).getText().toString();
        this.password = ((EditText)findViewById(R.id.password)).getText().toString();
        String chk_password = ((EditText)findViewById(R.id.chk_password)).getText().toString();
        this.phone = ((EditText)findViewById(R.id.phone)).getText().toString();
        this.address = ((EditText)findViewById(R.id.address)).getText().toString();

        RadioGroup r = (RadioGroup)findViewById(R.id.sex);
        int selectedId = r.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        this.sex = radioButton.getText().toString();

        createUser(email, password);
    }

    private void createUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                            FirebaseUser user = auth.getCurrentUser();
                            createProfile(user.getUid());
                        }else{
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Registration Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void createProfile(String uid){
        User user = new User(this.email,this.phone,this.sex,this.address);
        user.create(uid);
        Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
    }
}
