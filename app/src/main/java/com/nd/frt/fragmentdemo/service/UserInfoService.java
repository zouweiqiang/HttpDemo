package com.nd.frt.fragmentdemo.service;

import android.content.Context;

import com.nd.frt.fragmentdemo.model.UserInfo;
import com.nd.frt.fragmentdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserInfoService implements IUserInfo {

    @Override
    public List<UserInfo> getUserInfos(Context context) {
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        InputStream is = context.getResources().openRawResource(R.raw.content);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray results = jsonObject.getJSONArray("results");
            int length = results.length();
            for (int i = 0; i < length; i++) {
                UserInfo userInfo = new UserInfo();
                JSONObject info = results.getJSONObject(i);
                JSONObject nameJsonObject = info.getJSONObject("name");
                userInfo.userName = nameJsonObject.getString("title") +
                        nameJsonObject.getString("first") +
                        nameJsonObject.getString("last");
                userInfo.content = info.getString("email");
                JSONObject picture = info.getJSONObject("picture");
                userInfo.avatarUrl = picture.getString("medium");
                userInfos.add(userInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userInfos;
    }

}
