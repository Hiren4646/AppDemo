package test.com.utils;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    public ArrayList<Bean> getJSON(String url) {
        ArrayList<Bean> list=new ArrayList<>();
        Bean bn;

        // Making HTTP request
        try {

            URL u = new URL(url);
            HttpURLConnection hup = (HttpURLConnection) u.openConnection();
            hup.connect();
            is = hup.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        try {
            JSONArray jsonArray=new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++)
            {
                bn=new Bean();
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                bn.setId(jsonObj.getInt("id"));
                bn.setName(jsonObj.getString("name"));
                JSONObject jsonInnerObj=jsonObj.getJSONObject("fromcentral");


                bn.setCar(jsonInnerObj.getString("car"));
                if (jsonInnerObj.has("train")) {
                    bn.setTrain(jsonInnerObj.getString("train"));
                }
                JSONObject jsonInnerObjSecond=jsonObj.getJSONObject("location");
                bn.setLatitude(jsonInnerObjSecond.getDouble("latitude"));
                bn.setLongitude(jsonInnerObjSecond.getDouble("longitude"));

                list.add(bn);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return list;

    }



}
