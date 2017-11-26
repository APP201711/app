package com.example.admin.eatfood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity2 extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    private String userUID;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(
                    @NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d("System Log", String.valueOf(user));
                Log.d("System Log", "登入初始化");
                if (user!=null) {
                    Log.d("onAuthStateChanged", "登入:"+ user.getUid());
                    userUID =  user.getUid();
                }else{
                    Log.d("onAuthStateChanged", "已登出");
                }
            }
        };

        //跳到註冊畫面
        btn_register = (Button) findViewById(R.id.btn_register);

        Button nextPageBtn = (Button) findViewById(R.id.btn_register);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity2.this, SignActivity.class);
                startActivity(intent);
            }
        });

    }
     @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }
    public void login(View v){


        String email = ((EditText)findViewById(R.id.email))
                .getText().toString();
        String password = ((EditText)findViewById(R.id.password))
                .getText().toString();

        Log.d("AUTH", email+"/"+password);
        Toast.makeText(this,"login init",Toast.LENGTH_LONG).show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login", "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "success",Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}

