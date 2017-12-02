package com.example.admin.eatfood;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by img21 on 2017/12/2.
 */

public class UserModel {


    public String email;
    public String phone;
    public String sex;
    public String address;

    public UserModel() {

    }

    public UserModel(String email, String phone, String sex, String address) {
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.address = address;
    }


//    public void create(String uid){
//
//        memberTable.child("member_table").child(uid).child("email").setValue(this.email);
//        memberTable.child("member_table").child(uid).child("phone").setValue(this.phone);
//        memberTable.child("member_table").child(uid).child("sex").setValue(this.sex);
//        memberTable.child("member_table").child(uid).child("address").setValue(this.address);
//    }
}
