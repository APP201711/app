package com.example.admin.eatfood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.Posts;


public class request_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        Posts pst = Posts.getPost(bundle.getInt("id"));
        Log.e("error_log_tag", pst.restaurant_branch);
        View requestView = inflater.inflate(R.layout.fragment_request, container, false);
        TextView cate = (TextView) requestView.findViewById(R.id.cate);
        TextView restaurant_name = (TextView) requestView.findViewById(R.id.restaurant_name);
        TextView restaurant_branch = (TextView) requestView.findViewById(R.id.restaurant_branch);
        TextView restaurant_address = (TextView) requestView.findViewById(R.id.restaurant_address);
        TextView date = (TextView) requestView.findViewById(R.id.date);
        TextView sex_limit = (TextView) requestView.findViewById(R.id.sex_limit);
        TextView people_limit = (TextView) requestView.findViewById(R.id.people_limit_);
        TextView content = (TextView) requestView.findViewById(R.id.content);

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
        people_limit.setText(String.valueOf(pst.people_limit));
        content.setText(pst.content);


        return requestView;
    }
}
