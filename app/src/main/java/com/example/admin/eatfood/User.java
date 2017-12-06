package com.example.admin.eatfood;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Boolean.valueOf;

/**
 * Created by ncut on 2017/12/4.
 */

public class User {

    public static User usr;

    public boolean LoginStatus;
    public String address;
    public String id;
    public String password;
    public String username;
    public String phone;
    public String sex;
    public Boolean UpdateStatus;

    public Boolean RegisterStatus;
    public String RegisterError;

    protected User (String username, String password){
        try {
            String result = connectDB.db("username="+username+"&password="+password+"&type=login");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject LoginStatus = jsonArray.getJSONObject(0);
                this.LoginStatus = Boolean.valueOf(LoginStatus.getString("status"));
                Log.e("LoginStatus", String.valueOf(this.LoginStatus));
                if(this.LoginStatus){
                    JSONObject Data = jsonArray.getJSONObject(0);
                    JSONObject _Data = Data.getJSONObject("data");
                    this.address = _Data.getString("address");
                    this.id = _Data.getString("id");
                    this.password = _Data.getString("password");
                    this.username = _Data.getString("username");
                    this.phone = _Data.getString("phone");
                    this.sex = _Data.getString("sex");
                    usr = this;
                }
//                Log.e("address", this.address);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
    }

    protected User (String username, String password,String address, String phone,String sex){
        if(check_usrname(username)){
            try {
                String result = connectDB.db("username="+username+"&password="+password+"&address="+address+"&phone="+phone+"&sex="+sex+"&type=register_user");
                    Log.e("result", result);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(result);
                        JSONObject LoginStatus = jsonArray.getJSONObject(0);
                        this.RegisterStatus = Boolean.valueOf(LoginStatus.getString("status"));
                        Log.e("RegisterStatus", String.valueOf(this.RegisterStatus));
//                if(this.RegisterStatus){
//                    JSONObject Data = jsonArray.getJSONObject(0);
//                    JSONObject _Data = Data.getJSONObject("data");
//                    this.address = _Data.getString("address");
//                    this.id = _Data.getString("id");
//                    this.password = _Data.getString("password");
//                    this.username = _Data.getString("username");
//                    this.phone = _Data.getString("phone");
//                    this.sex = _Data.getString("sex");
//                    usr = this;
//                }
//                Log.e("address", this.address);
                } catch (JSONException e1) {
                    Log.e("error_log_tag", e1.toString());
                }

            } catch(Exception e) {
                Log.e("error_log_tag", e.toString());
            }
        }else{
            this.RegisterStatus = Boolean.valueOf(false);
            this.RegisterError = "帳號已經有人使用";
        }

    }

    protected boolean check_usrname(String username){
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

    protected static void update(){
        Log.e("first address", usr.address);
        try {
            String result = connectDB.db("query_string= update `user` set address = '"+usr.address+"', phone = '"+usr.phone+"' , password = '"+usr.password+"'"+"where `username` = '" + usr.username +"'"+"&type=update_user");
            Log.e("Update result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject LoginStatus = jsonArray.getJSONObject(0);
                usr.UpdateStatus = Boolean.valueOf(LoginStatus.getString("status"));
                Log.e("LoginStatus", String.valueOf(usr.UpdateStatus));
                Log.e("address", usr.address);
            } catch (JSONException e1) {
                e1.printStackTrace();
                usr.UpdateStatus =false;
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
            usr.UpdateStatus =false;
        }
    }

    protected static User getUsr(){
        return usr;
    }

}
