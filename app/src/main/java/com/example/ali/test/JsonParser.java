package com.example.ali.test;

import android.net.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ali on 2/8/2017.
 */

public class JsonParser extends SuperParser {
    String json;
    String[] objects;
    List<String> posterPathUrls = new ArrayList<>();
    //SuperParser abstract methods
    @Override
    public void setData(String json) {
        this.json = json;
    }
    @Override
    public List<String> getData() {
        try {
            parseJson(new JSONObject(this.json));
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
        JSONArray jsonArr = (JSONArray) object2;
        for (int k = 0; k < jsonArr.length(); k++) {
            if (jsonArr.get(k) instanceof JSONObject) {
                parseJson((JSONObject) jsonArr.get(k));
            } else {
            }

        }
    }

    public void parseJson(JSONObject jsonObject) throws ParseException, JSONException {
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (jsonObject.get(obj.toString()) instanceof JSONArray) {
                getArray(jsonObject.get(obj.toString()));
            } else {
                if (jsonObject.get(obj.toString()) instanceof JSONObject) {
                    parseJson((JSONObject) jsonObject.get(obj.toString()));
                } else {
                    if (obj.toString().equals("poster_path")){
                        posterPathUrls.add(jsonObject.get(obj.toString()).toString());
                    }
                }
            }
        }
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