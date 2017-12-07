package com.example.admin.eatfood;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by img21 on 2017/12/7.
 */

public class Posts {

    protected int id;
    protected String restaurant_name;
    protected String restaurant_branch;
    protected String meeting_date;
    protected int sex_limit;
    protected int people_limit;
    protected String content;
    protected int owner_id;

    protected static Posts getPost(int id){
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
            pst.restaurant_branch = _Data.getString("restaurant_branch");
            pst.meeting_date = _Data.getString("meeting_date");
            pst.sex_limit = Integer.parseInt(_Data.getString("sex_limit"));
            pst.people_limit = Integer.parseInt(_Data.getString("people_limit"));
            pst.content = _Data.getString("content");
            pst.owner_id = Integer.parseInt(_Data.getString("owner_id"));
        } catch(Exception e) {
            Log.e("error_log_tag", e.toString());
        }
        return pst;
    }


    protected static boolean createPost(int cate_id, String restaurant_name, String restaurant_branch, String meeting_date, int sex_limit, int people_limit, String content, int owner_id){
        Boolean CreateStatus = false;
        try {
            String result = connectDB.db("cate_id="+cate_id+"&restaurant_name="+restaurant_name+"&restaurant_branch="+restaurant_branch+"&meeting_date="+meeting_date+"&sex_limit="+sex_limit+"&people_limit="+people_limit+"&content="+content+"&owner_id="+owner_id+"&type=create_post");
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

    protected static String[][] getCate(){
        String[][] cate = new String[0][2];
        try {
            String result = connectDB.db("type=get_cate");
            Log.e("result", result);
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(result);
                cate = new String[jsonArray.length()][2];
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    cate[i][0] =  jsonData.getString("id");
                    cate[i][1] =  jsonData.getString("name");
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
        Log.e("update", this.content);
        try {
            String result = connectDB.db("id="+this.id+"&restaurant_name="+this.restaurant_name+"&restaurant_branch="+this.restaurant_branch+"&meeting_date="+this.meeting_date+"&sex_limit="+this.sex_limit+"&people_limit="+this.people_limit+"&content="+this.content+"&type=update_post");
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
}