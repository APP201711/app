package com.example.admin.eatfood.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.Posts;
import com.example.admin.eatfood.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Home_Fragment extends Fragment {
    /*  get  all  posts */
    Posts[] pst = Posts.all();

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View HomeView = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = (ListView) HomeView.findViewById(R.id.list);

        List<HashMap<String , String>> list = new ArrayList<>();
        //使用List存入HashMap，用來顯示ListView上面的文字。

        for(int i = 0 ; i < pst.length ; i++){
            HashMap<String , String> hashMap = new HashMap<>();
//            hashMap.put("title" , pst[i].owner.username);
            hashMap.put("title" , pst[i].restaurant_name+"  "+pst[i].restaurant_branch);
            hashMap.put("text" , pst[i].meeting_date);
//            hashMap.put("address" , pst[i].restaurant_address);
//            hashMap.put("date" , pst[i].meeting_date);
//            hashMap.put("number" , String.valueOf(pst[i].people_limit));
            //把title , text存入HashMap之中
            list.add(hashMap);
            //把HashMap存入list之中
        }

        ListAdapter listAdapter = new SimpleAdapter(
                getActivity(),
                list,
                android.R.layout.simple_list_item_2 ,
                new String[]{"title" , "text","store","address","date","number"} ,
                new int[]{android.R.id.text1 , android.R.id.text2});
        // 5個參數 : context , List , layout , key1 & key2 , text1 & text2

        listView.setAdapter(listAdapter);

        return HomeView;
    }


}
