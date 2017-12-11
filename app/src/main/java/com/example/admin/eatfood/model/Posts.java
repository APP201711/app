package com.example.admin.eatfood.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by img21 on 2017/12/7.
 */

public class Posts {

    public int id;
    public int cate_id;
    public String cate_name;
    public String restaurant_name;
    public String restaurant_branch;
    public String restaurant_address;
    public String meeting_date;
    public int sex_limit;
    public int people_limit;
    public String content;
    public int owner_id;
    public User owner;
    public Boolean reqstatus; /* 是否提出申請 */

    public Posts(){}

    public static Posts getPost(int id){
        Posts pst = new Posts();
        try {
            String result = connectDB.db("id="+id+"&type=get_post");
            Log.e("result", result);
            //JSONObject LoginStatus = jsonArray.getJSONObject(0);
            //pst.LoginStatus = Boolean.valueOf(LoginStatus.getString("status"));
            //Log.e("LoginStatus", String.valueOf(pst.LoginStatus));
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(result);
            JSONObject Data = jsonArray.getJSONObject(0);
            JSONObject _Data = Data.getJSONObject("data");
            pst.restaurant_name = _Data.getString("restaurant_name");
            pst.id = Integer.parseInt(_Data.getString("id"));
            pst.cate_id = Integer.parseInt(_Data.getString("cate_id"));
            pst.cate_name = _Data.getString("category_name");
            pst.restaurant_branch = _Data.getString("restaurant_branch");
            pst.restaurant_address = _Data.getString("restaurant_address");
            pst.meeting_date = _Data.getString("meeting_date");
            pst.sex_limit = Integer.parseInt(_Data.getString("sex_limit"));
            pst.people_limit = Integer.parseInt(_Data.getString("people_limit"));
            pst.content = _Data.getString("content");
            pst.owner_id = Integer.parseInt(_Data.getString("owner_id"));
            pst.owner = User.getUsr(pst.owner_id);
        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return pst;
    }


    public boolean create(){
        Boolean CreateStatus = false;
        try {
            String result = connectDB.db("cate_id="+this.cate_id+"&restaurant_name="+this.restaurant_name+"&restaurant_branch="+this.restaurant_branch+"&restaurant_address="+this.restaurant_address+"&meeting_date="+this.meeting_date+"&sex_limit="+this.sex_limit+"&people_limit="+this.people_limit+"&content="+this.content+"&owner_id="+this.owner_id+"&type=create_post");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject Status = jsonArray.getJSONObject(0);
                CreateStatus = Boolean.valueOf(Status.getString("status"));
                Log.e("CreateStatus", String.valueOf(CreateStatus));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return CreateStatus;
    }

    public static String[] getCate(){
        String[] cate = new String[0];
        try {
            String result = connectDB.db("type=get_cate");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                cate = new String[jsonArray.length()];
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
//                    cate[i][0] =  jsonData.getString("id");
                    cate[i] =  jsonData.getString("name");
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return cate;
    }

    public void update() {
        try {
            String result = connectDB.db("id="+this.id+"&restaurant_name="+this.restaurant_name+"&restaurant_branch="+this.restaurant_branch+"&restaurant_address="+this.restaurant_address+"&meeting_date="+this.meeting_date+"&sex_limit="+this.sex_limit+"&people_limit="+this.people_limit+"&content="+this.content+"&type=update_post");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject Status = jsonArray.getJSONObject(0);
                Boolean UpdateStatus = Boolean.valueOf(Status.getString("status"));
                Log.e("UpdateStatus", String.valueOf(UpdateStatus));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
    }

    public void delete() {
        Log.e("delete", String.valueOf(this.id));
        try {
            String result = connectDB.db("id="+this.id+"&type=delete_post");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                JSONObject Status = jsonArray.getJSONObject(0);
                Boolean UpdateStatus = Boolean.valueOf(Status.getString("status"));
                Log.e("DeleteStatus", String.valueOf(UpdateStatus));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
    }

    public static Posts[] all() {
        Posts pst[] = new Posts[5];
        try {
            String result = connectDB.db("type=all_post");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                pst = new Posts[jsonArray.length()];
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    pst[i] = new Posts();
                    pst[i].id = Integer.parseInt(jsonData.getString("id"));
                    pst[i].cate_id = Integer.parseInt(jsonData.getString("cate_id"));
                    pst[i].cate_name = jsonData.getString("category_name");
                    pst[i].restaurant_name = jsonData.getString("restaurant_name");
                    pst[i].restaurant_branch = jsonData.getString("restaurant_branch");
                    pst[i].restaurant_address = jsonData.getString("restaurant_address");
                    pst[i].meeting_date = jsonData.getString("meeting_date");
                    pst[i].sex_limit = Integer.parseInt(jsonData.getString("sex_limit"));
                    pst[i].content = jsonData.getString("content");
                    pst[i].owner_id = Integer.parseInt(jsonData.getString("owner_id"));
                    pst[i].owner = User.getUsr(pst[i].owner_id);
                    pst[i].reqstatus = Orders.check(pst[i]);
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return pst;
    }
}
