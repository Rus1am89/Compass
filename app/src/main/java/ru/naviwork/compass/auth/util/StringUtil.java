package ru.naviwork.compass.auth.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.naviwork.compass.auth.data.AuthToken;

public class StringUtil {

    public static String removeHtmlAtribute(String html) {
        return html.replaceAll("<.*?>", "");
    }

    public static AuthToken getAuthToken(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, AuthToken.class);
    }


}
