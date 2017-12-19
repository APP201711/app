package com.example.admin.eatfood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.Orders;
import com.example.admin.eatfood.model.Posts;
import com.example.admin.eatfood.model.User;


public class request_Fragment extends Fragment {

    int lasts = 0 ;
    Posts pst;
    Button request;
    User usr = User.getUsr();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        pst = Posts.getPost(bundle.getInt("id"));
        Log.e("error_log_tag", pst.restaurant_branch);
        View requestView = inflater.inflate(R.layout.fragment_request, container, false);
        TextView cate = (TextView) requestView.findViewById(R.id.cate);
        TextView restaurant_name = (TextView) requestView.findViewById(R.id.restaurant_name);
        TextView restaurant_branch = (TextView) requestView.findViewById(R.id.restaurant_branch);
        TextView restaurant_address = (TextView) requestView.findViewById(R.id.restaurant_address);
        TextView date = (TextView) requestView.findViewById(R.id.date);
        TextView sex_limit = (TextView) requestView.findViewById(R.id.sex_limit);
        TextView people_limit = (TextView) requestView.findViewById(R.id.people_limit_);
        TextView last_people = (TextView) requestView.findViewById(R.id.last_people);
        TextView content = (TextView) requestView.findViewById(R.id.content);
        request = (Button) requestView.findViewById(R.id.btn_request);

        cate.setText(pst.cate_name);
        restaurant_name.setText(pst.restaurant_name);
        restaurant_branch.setText(pst.restaurant_branch);
        restaurant_address.setText(pst.restaurant_address);
        date.setText(pst.meeting_date);

        if(pst.sex_limit == 0){
            sex_limit.setText("不限");
        }else if(pst.sex_limit == 1){
            sex_limit.setText("限制男生");
        }else{
            sex_limit.setText("限制女生");
        }

        lasts = pst.people_limit-Orders.complete_orders(pst);
        people_limit.setText(String.valueOf(pst.people_limit));
        last_people.setText(String.valueOf(lasts));
        content.setText(pst.content);

        if(usr.id == pst.owner_id || lasts == 0 || !(Orders.check(pst)) || (pst.sex_limit == 1 && usr.sex.equals(0)) || bundle.getString("order") != null || (pst.sex_limit == 2 && usr.sex.equals(1))){
            request.setVisibility(View.INVISIBLE);
        }
        if(!(Orders.check(pst))){
            Toast.makeText(getActivity(),
                    "您已送出要求,請在[歷史資料]查看回復.", Toast.LENGTH_LONG).show();
        }

        if(usr.id == pst.owner_id){
            Toast.makeText(getActivity(),
                    "這篇是您的文章.", Toast.LENGTH_LONG).show();
        }


        request.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                request();
            }
        });

        return requestView;
    }

    public void request(){
        Orders.request(pst);
        request.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(),
                "送出完成", Toast.LENGTH_LONG).show();
    }
}
