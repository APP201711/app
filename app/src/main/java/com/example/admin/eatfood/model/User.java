package com.example.admin.eatfood.model;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ncut on 2017/12/4.
 */

public class User {

    public static User usr = new User();

    public Boolean LoginStatus = false;
    public String address;
    public int id;
    public String password;
    public String username;
    public String phone;
    public String sex;

    public User(){

    }

    public static User login(String username, String password){
        User user = new User();
        try {
            String result = connectDB.db("username="+username+"&password="+password+"&type=login");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject LoginStatus = jsonArray.getJSONObject(0);
                user.LoginStatus = LoginStatus.getBoolean("status");
                Log.e("LoginStatus", LoginStatus.getString("status"));
                if(user.LoginStatus){
                    JSONObject Data = jsonArray.getJSONObject(0);
                    JSONObject _Data = Data.getJSONObject("data");
                    user.address = _Data.getString("address");
                    user.id = _Data.getInt("id");
                    user.password = _Data.getString("password");
                    user.username = _Data.getString("username");
                    user.phone = _Data.getString("phone");
                    user.sex = _Data.getString("sex");
                    usr = user;
                }

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return user;
    }

    public static Boolean register (String username, String password,String address, String phone,String sex){
        Boolean RegisterStatus = false;
        String RegisterError = "";
        if(check_usrname(username)){
            try {
                String result = connectDB.db("username="+username+"&password="+password+"&address="+address+"&phone="+phone+"&sex="+sex+"&type=register_user");
                    Log.e("result", result);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(result);
                        JSONObject LoginStatus = jsonArray.getJSONObject(0);
                        RegisterStatus = Boolean.valueOf(LoginStatus.getString("status"));
                        Log.e("RegisterStatus", String.valueOf(RegisterStatus));
                } catch (JSONException e1) {
                    Log.e("error_log_tag", e1.toString());
                }

            } catch(Exception e) {
                Log.e("error_log_tag", e.toString());
            }
        }else{
            RegisterStatus = Boolean.FALSE;
        }
        return RegisterStatus;
    }

    public static boolean check_usrname(String username){
        boolean chk = false;
        try {
            String result = connectDB.db("username="+username+"&type=check_usrname");
            Log.e("chk_username", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject LoginStatus = jsonArray.getJSONObject(0);
                chk = Boolean.valueOf(LoginStatus.getString("status"));
                Log.e("check_usrname", String.valueOf(chk));
            } catch (JSONException e1) {
                Log.e("error_log_tag", e1.toString());
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return chk;
    }

    public boolean update(){
        Boolean UpdateStatus = false;
        Log.e("first address", this.address);
        try {
            String result = connectDB.db("query_string= update `user` set address = '"+this.address+"', phone = '"+this.phone+"' , password = '"+this.password+"'"+"where `username` = '" + this.username +"'"+"&type=update_user");
            Log.e("Update result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject LoginStatus = jsonArray.getJSONObject(0);
                UpdateStatus = Boolean.valueOf(LoginStatus.getString("status"));
                Log.e("LoginStatus", String.valueOf(UpdateStatus));
                Log.e("address", this.address);
            } catch (JSONException e1) {
                e1.printStackTrace();
                UpdateStatus =false;
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
            UpdateStatus =false;
        }
        return UpdateStatus;
    }

    public static User getUsr(){
        return usr;
    }

    public static User getUsr(int id){
        User usr = new User();
        try {
            String result = connectDB.db("id="+id+"&type=get_user");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject getUserStatus = jsonArray.getJSONObject(0);
                JSONObject Data = jsonArray.getJSONObject(0);
                JSONObject _Data = Data.getJSONObject("data");
                usr.address = _Data.getString("address");
                usr.id = _Data.getInt("id");
                usr.username = _Data.getString("username");
                usr.phone = _Data.getString("phone");
                usr.sex = _Data.getString("sex");

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return usr;
    }

}
