package com.example.ali.test;

import android.net.ParseException;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Ali on 2/8/2017.
 */

public class JsonParser extends SuperParser {
    String json;
    String[] objects;
    List<String> ppp = new ArrayList<>();
    public void setData(String json) {
        this.json = json;
    }

    @Override
    public List<String> getData() {
        try {
            String[] data= getPosterURLs();
            parseJson(new JSONObject(this.json));
            return ppp;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setObjects(String[] objects) {
        this.objects=objects;
    }

    public String[] getPosterURLs() throws JSONException {
        JSONObject movieJson = new JSONObject(this.json);
//        Log.v("MainActivity","Keys: "+movieJson.keys().next());
//        Iterator<?> keys = movieJson.keys();
//        while (keys.hasNext()) {
//            String key = (String) keys.next();
//            Log.v("JsonTest", "Keys: " + key);
//        }
        JSONArray jsonArray = movieJson.optJSONArray("results");
        JSONObject child;
        String[] posterURLs = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            child = jsonArray.getJSONObject(i);
            posterURLs[i] = "http://image.tmdb.org/t/p/w185/";
            posterURLs[i] += child.optString("poster_path");
        }
        return posterURLs;
    }

    public  void getArray(Object object2) throws ParseException, JSONException {
//Log.v("JsonTest","getArray:"+object2.toString());
        JSONArray jsonArr = (JSONArray) object2;
//        Log.v("JsonTest","JsonArr: "+jsonArr.toString());
        for (int k = 0; k < jsonArr.length(); k++) {

            if (jsonArr.get(k) instanceof JSONObject) {
//                Log.v("JsonTest","JO : jsonArr.get(k)"+jsonArr.get(k));

                parseJson((JSONObject) jsonArr.get(k));
            } else {
//                Log.v("JsonTest","Else : jsonArr.get(k)"+jsonArr.get(k));
            }

        }
    }

    public  void parseJson(JSONObject jsonObject) throws ParseException, JSONException {
//        Log.v("JsonTest","ParseJson"+jsonObject.toString());
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
//            Log.v("JsonTest","Key :"+(String)obj);
                if (jsonObject.get(obj.toString()) instanceof JSONArray) {
//                    Log.v("JsonTest", "JsonArray :" + obj.toString());
                    getArray(jsonObject.get(obj.toString()));
                } else {
                    if (jsonObject.get(obj.toString()) instanceof JSONObject) {
                        parseJson((JSONObject) jsonObject.get(obj.toString()));
                    } else {
                        if (obj.toString().equals("poster_path")){
                            ppp.add(jsonObject.get(obj.toString()).toString());
                        }
//                        Log.v("JsonTest", "else Json: " + obj.toString() + "\t"
//                                + jsonObject.get(obj.toString()));
                    }
                }


        }
    }
}