package com.example.admin.eatfood.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by img21 on 2017/12/8.
 */

public class Orders {
    public int order_id;
    public int post_id;
    public Posts post;
    public User user;
    public int user_id;
    public int status;
    public int user_check;

    public static boolean request(Posts pst){   // 新增要求
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


    public Boolean response(int res){   // 回應要求  1接受 2拒絕
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


    public static boolean check(Posts pst){   //查看是否重複提出申請
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

    public static Orders[] orders_for_posts(){
        Orders ord[] = new Orders[0];
        User usr = User.getUsr();
        try {
            String result = "";
            result = connectDB.db("owner_id="+usr.id+"&type=orders_for_posts");
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

    public static Orders[] orders_for_users(){
        Orders ord[] = new Orders[0];
        User usr = User.getUsr();
        try {
            String result = "";
            result = connectDB.db("owner_id="+usr.id+"&type=orders_for_users");
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
