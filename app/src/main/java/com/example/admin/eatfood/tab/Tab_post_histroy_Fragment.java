package com.example.admin.eatfood.tab;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.admin.eatfood.R;
import com.example.admin.eatfood.fragment.accept_Fragment;
import com.example.admin.eatfood.fragment.request_Fragment;
import com.example.admin.eatfood.model.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Tab_post_histroy_Fragment extends Fragment {
    Orders[] ords = Orders.orders_for_posts();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View postHistroy = inflater.inflate(R.layout.fragment_tab_post_history, container, false);
        ListView listView = (ListView) postHistroy.findViewById(R.id.post_list);

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
            hashMap.put("text" , "狀態:"+status+"   來自: "+ords[i].user.username+" 的請求");
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                HashMap<String , String> data = (HashMap<String , String>) listView.getItemAtPosition(position);
                final int _id = Integer.parseInt(data.get("id"));
                accept_Fragment mFrag = new accept_Fragment();
                Bundle bnd = new Bundle();
                bnd.putInt("post_id",ords[_id].post_id) ;
                bnd.putInt("usr_id",ords[_id].user_id) ;
                bnd.putInt("ord_id",_id) ;
                mFrag.setArguments(bnd);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contendor,mFrag).commit();
            }


        });
        return  postHistroy;
    }
}
