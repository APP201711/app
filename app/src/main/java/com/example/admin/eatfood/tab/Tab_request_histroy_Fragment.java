package com.example.admin.eatfood.tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.model.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Tab_request_histroy_Fragment extends Fragment {
    Orders[] ords = Orders.orders_for_users();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View requestHistroy = inflater.inflate(R.layout.fragment_tab_request_history, container, false);
        ListView listView = (ListView) requestHistroy.findViewById(R.id.req_list);

        List<HashMap<String , String>> list = new ArrayList<>();
        //使用List存入HashMap，用來顯示ListView上面的文字。

        for(int i = 0 ; i < ords.length ; i++){
            String status;
            if(ords[i].status == 0){
                status = "未回覆";
            }else if(ords[i].status == 1){
                status = "成功";
            }else{
                status = "失敗";
            }

            HashMap<String , String> hashMap = new HashMap<>();
//            hashMap.put("title" , pst[i].owner.username);
            hashMap.put("title" , ords[i].post.restaurant_name+"  "+ords[i].post.restaurant_branch);
            hashMap.put("id" , String.valueOf(i));
            hashMap.put("text" , "狀態:"+status);
            //把title , text存入HashMap之中
            list.add(hashMap);
            //把HashMap存入list之中
        }
        ListAdapter listAdapter = new SimpleAdapter(
                getActivity(),
                list,
                android.R.layout.simple_list_item_2 ,
                new String[]{"title" , "id","text"} ,
                new int[]{android.R.id.text1 , android.R.id.text2, android.R.id.text2});
        listView.setAdapter(listAdapter);
        return requestHistroy;
    }
}
