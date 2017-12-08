package com.example.admin.eatfood;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by img21 on 2017/12/8.
 */

public class Orders {
    protected int order_id;
    protected int post_id;
    protected Posts post;
    protected User user;
    protected int user_id;
    protected int status;
    protected int user_check;

    protected static boolean request(Posts pst){   // 新增要求
        User usr = User.getUsr();
        boolean status = false;
        if(Orders.check(pst)){
            try {
                String result = connectDB.db("post_id="+pst.id+"&user_id="+usr.id+"&type=create_order");
                Log.e("check", result);
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(result);
                    JSONObject LoginStatus = jsonArray.getJSONObject(0);
                    status = Boolean.valueOf(LoginStatus.getString("status"));
                    Log.e("check", String.valueOf(status));
                } catch (JSONException e1) {
                    Log.e("error_log_tag", e1.toString());
                }

            } catch(Exception e) {
                Log.e("error_log_tag", e.toString());
            }
        }
        return status;
    }


    protected Boolean response(int res){   // 回應要求  1接受 2拒絕
        Boolean status = false;
            try {
                String result = connectDB.db("order_id="+this.order_id+"&response="+res+"&type=res_order");
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(result);
                    JSONObject LoginStatus = jsonArray.getJSONObject(0);
                    status = Boolean.valueOf(LoginStatus.getString("status"));
                    Log.e("check", String.valueOf(status));
                } catch (JSONException e1) {
                    Log.e("error_log_tag", e1.toString());
                }

            } catch(Exception e) {
                Log.e("error_log_tag", e.toString());
            }
        return status;
    }


    protected static boolean check(Posts pst){   //查看是否重複提出申請
        User usr = User.getUsr();
        boolean chk = false;
        try {
            String result = connectDB.db("post_id="+pst.id+"&user_id="+usr.id+"&type=check_usrname");
            Log.e("check", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject LoginStatus = jsonArray.getJSONObject(0);
                chk = Boolean.valueOf(LoginStatus.getString("status"));
                Log.e("check", String.valueOf(chk));
            } catch (JSONException e1) {
                Log.e("error_log_tag", e1.toString());
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return chk;
    }

    protected static Orders[] orders_all(int n){   /* 發布者 列表 -0:未回應 1:所有列表 */
        Orders ord[] = new Orders[0];
        User usr = User.getUsr();
        try {
            String result = "";
            if(n == 0){
                result = connectDB.db("owner_id="+usr.id+"&type=get_orders");
            }else{
                result = connectDB.db("owner_id="+usr.id+"&type=orders_all");
            }
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                ord = new Orders[jsonArray.length()];
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    ord[i].order_id = jsonData.getInt("order_id");
                    ord[i].post_id = jsonData.getInt("post_id");
                    ord[i].post = Posts.getPost(ord[i].post_id);
                    ord[i].user_id = jsonData.getInt("user_id");
                    ord[i].user = User.getUsr(ord[i].user_id);
                    ord[i].status = jsonData.getInt("status");
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return ord;
    }


}
