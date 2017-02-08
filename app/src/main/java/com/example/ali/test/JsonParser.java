package com.example.ali.test;

import android.net.ParseException;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ali on 2/8/2017.
 */

public class JsonParser extends SuperParser {
    String json;
    String[] objects;
    List<String> posterPathUrls = new ArrayList<>();
    List<HashMap<String,String>> maps = new ArrayList<>();
    //SuperParser abstract methods
    @Override
    public void setData(String json) {
        this.json = json;
    }
    @Override
    public List<String> getData() {
        try {
            parseJson(new JSONObject(this.json));
            for (int i=0; i<maps.size(); i++)
            Log.v("JSONTest","OverView: "+maps.get(i).get("id"));
            return posterPathUrls;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void setObjects(String[] objects) {
        this.objects=objects;
    }

    //Parse Json
    public  void getArray(Object object2) throws ParseException, JSONException {
        Log.v("JSONTest","getArray :"+object2);
        JSONArray jsonArr = (JSONArray) object2;
        for (int k = 0; k < jsonArr.length(); k++) {
            if (jsonArr.get(k) instanceof JSONObject) {
                parseJson((JSONObject) jsonArr.get(k));
            } else {
                Log.v("JSONTest","jsonArr.get(k): "+jsonArr.get(k));
            }

        }
    }

    public void parseJson(JSONObject jsonObject) throws ParseException, JSONException {
        Log.v("JSONTest","ParseJson :"+jsonObject);
        Iterator<String> iterator = jsonObject.keys();
        HashMap<String,String> map = new HashMap<>();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (jsonObject.get(obj.toString()) instanceof JSONArray) {
                getArray(jsonObject.get(obj.toString()));
            } else {
                if (jsonObject.get(obj.toString()) instanceof JSONObject) {
                    parseJson((JSONObject) jsonObject.get(obj.toString()));
                } else {
                    if( Arrays.asList(objects).contains(obj.toString())){
                        Log.v("JSONTest","Objects :"+jsonObject.get(obj.toString()).toString());
                    }
//                    for (String x:objects){
//                        if (obj.toString().equals(x)){
//                            Log.v("JSONTest","Objects :"+jsonObject.get(obj.toString()).toString());
//                        }
//                    }
                    if (obj.toString().equals("poster_path")){
                        posterPathUrls.add(jsonObject.get(obj.toString()).toString());
                    }
                    map.put(obj.toString(),jsonObject.get(obj.toString()).toString());
                    Log.v("JSONTest","else : "+obj+"  ,  "+jsonObject.get(obj.toString()));
                }
            }
        }
        maps.add(map);
    }

//    public String[] getPosterURLs() throws JSONException {
//        JSONObject movieJson = new JSONObject(this.json);
//        JSONArray jsonArray = movieJson.optJSONArray("results");
//        JSONObject child;
//        String[] posterURLs = new String[jsonArray.length()];
//        for (int i = 0; i < jsonArray.length(); i++) {
//            child = jsonArray.getJSONObject(i);
//            posterURLs[i] = "http://image.tmdb.org/t/p/w185/";
//            posterURLs[i] += child.optString("poster_path");
//        }
//        return posterURLs;
//    }
}