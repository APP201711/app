package com.example.admin.eatfood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.User;

import org.w3c.dom.Text;


public class personal_Fragment extends Fragment {
    User usr = User.getUsr();
    TextView password;
    TextView username;
    EditText new_password;
    EditText phone;
    EditText address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View personalView = inflater.inflate(R.layout.fragment_personal, container, false);
        username = (TextView)personalView.findViewById(R.id.username);
        password = (EditText)personalView.findViewById(R.id.password);
        new_password = (EditText)personalView.findViewById(R.id.newpassword);
        phone = (EditText)personalView.findViewById(R.id.phone);
        address = (EditText)personalView.findViewById(R.id.address);

        username.setText(usr.username);
        phone.setText(usr.phone);
        address.setText(usr.address);
        Button submit = (Button)personalView.findViewById(R.id.btnupdate);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                UserUpdate();
            }
        });

//        personalView.setFocusableInTouchMode(true);
//        personalView.requestFocus();
//        personalView.setOnKeyListener( new View.OnKeyListener()
//        {
//            @Override
//            public boolean onKey( View v, int keyCode, KeyEvent event )
//            {
//                if( keyCode == KeyEvent.KEYCODE_BACK )
//                {
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.contendor,new Home_Fragment()).commit();
//                }
//                return false;
//            }
//        } );


        return personalView;
    }




    public void UserUpdate(){
        if(password.getText().toString().equals(usr.password)){
            if(!(new_password.getText().toString().isEmpty())){
                usr.password = new_password.getText().toString();
            }
            usr.phone = phone.getText().toString();
            usr.address = address.getText().toString();
            usr.update();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contendor,new Home_Fragment()).commit();
            Toast.makeText(getActivity(),
                    "修改完成", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),
                    "密碼錯誤", Toast.LENGTH_LONG).show();

        }

    }
}
