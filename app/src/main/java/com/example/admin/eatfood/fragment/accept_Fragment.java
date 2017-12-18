package com.example.admin.eatfood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.Orders;
import com.example.admin.eatfood.model.Posts;
import com.example.admin.eatfood.model.User;


public class accept_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View accept = inflater.inflate(R.layout.fragment_accept, container, false);
        TextView resname = (TextView) accept.findViewById(R.id.Resname);
        TextView rescate = (TextView) accept.findViewById(R.id.Rescate);
        TextView resaddr = (TextView) accept.findViewById(R.id.Resaddr);
        TextView date = (TextView) accept.findViewById(R.id.Postdate);
        TextView people = (TextView) accept.findViewById(R.id.Postpeople);
        TextView usrname = (TextView) accept.findViewById(R.id.Usrname);
        TextView usrphone = (TextView) accept.findViewById(R.id.Usrphone);


        Bundle bundle = this.getArguments();
        Posts pst = Posts.getPost(bundle.getInt("post_id"));
        User usr = User.getUsr(bundle.getInt("usr_id"));
        final int ord_id = bundle.getInt("ord_id");
        resname.setText(pst.restaurant_name);
        rescate.setText(pst.restaurant_branch);
        resaddr.setText(pst.restaurant_address);
        date.setText(pst.meeting_date);
        int lasts = pst.people_limit-Orders.complete_orders(pst);
        people.setText(String.valueOf(lasts));
        usrname.setText(usr.username);
        usrphone.setText(usr.phone);


        Button btn_a = (Button) accept.findViewById(R.id.btn_accept);
        btn_a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Log.e("ord_id", String.valueOf(ord_id));
                Orders.response(ord_id,1);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contendor,new history_Fragment()).commit();
            }
        });

        Button btn_b = (Button) accept.findViewById(R.id.btn_reject);
        btn_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Orders.response(ord_id,2);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contendor,new history_Fragment()).commit();
            }
        });

        return accept;
    }
}
