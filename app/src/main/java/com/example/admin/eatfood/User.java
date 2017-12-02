package com.example.admin.eatfood;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by img21 on 2017/12/2.
 */

public class User{
    DatabaseReference memberTable =  FirebaseDatabase.getInstance().getReference();

    FirebaseAuth auth;
    public String email;
    public String phone;
    public String sex;
    public String address;
    public boolean status = false;

    public User(){
//        FirebaseUser user = auth.getCurrentUser();
//        String uid = user.getUid().toString();
        String uid = "03Ic1jXEoRU5dvE3mwNE5SGwKQ42";
        memberTable.child("member_table").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel usr = dataSnapshot.getValue(UserModel.class);
                dataset(usr);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    public User(String email, String phone, String sex, String address) {
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.address = address;
    }

    public void create(String uid){
        UserModel user1 = new UserModel(this.email,this.phone,this.sex,this.address);
        memberTable.child("member_table").child(uid).setValue(user1);
    }

    private void dataset(UserModel usr) {
        this.email = usr.email;
        this.address = usr.address;
        this.phone = usr.phone;
        this.sex = usr.sex;
        this.status = true;
    }
}
