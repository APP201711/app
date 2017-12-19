package com.example.admin.eatfood.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

/**
 * Created by ADMIN on 2017/12/19.
 */

public class show_Fragment extends Fragment {
    Posts pst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        pst = Posts.getPost(bundle.getInt("id"));
        View requestView = inflater.inflate(R.layout.fragment_show, container, false);
        TextView owner_name = (TextView) requestView.findViewById(R.id.post_id);
        TextView cate = (TextView) requestView.findViewById(R.id.cate);
        TextView restaurant_name = (TextView) requestView.findViewById(R.id.restaurant_name);
        TextView restaurant_branch = (TextView) requestView.findViewById(R.id.restaurant_branch);
        TextView restaurant_address = (TextView) requestView.findViewById(R.id.restaurant_address);
        TextView date = (TextView) requestView.findViewById(R.id.date);
        TextView sex_limit = (TextView) requestView.findViewById(R.id.sex_limit);
        TextView people_limit = (TextView) requestView.findViewById(R.id.people_limit_);
        TextView last_people = (TextView) requestView.findViewById(R.id.last_people);
        TextView content = (TextView) requestView.findViewById(R.id.content);

        owner_name.setText(pst.owner.username);
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

        int lasts = pst.people_limit- Orders.complete_orders(pst);
        people_limit.setText(String.valueOf(pst.people_limit));
        last_people.setText(String.valueOf(lasts));
        content.setText(pst.content);


        return requestView;
    }

}
