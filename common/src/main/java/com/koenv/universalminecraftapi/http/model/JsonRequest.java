package com.koenv.universalminecraftapi.http.model;

import com.koenv.universalminecraftapi.methods.ExcludeFromDoc;
import com.koenv.universalminecraftapi.util.json.JSONArray;
import com.koenv.universalminecraftapi.util.json.JSONException;
import com.koenv.universalminecraftapi.util.json.JSONObject;
import com.koenv.universalminecraftapi.util.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

@ExcludeFromDoc
public class JsonRequest {
    private String expression;
    private String tag;

    public JsonRequest(String expression, String tag) {
        this.expression = expression;
        this.tag = tag;
    }

    public String getExpression() {
        return expression;
    }

    public String getTag() {
        return tag;
    }

    public static JsonRequest fromJson(JSONObject json) throws JSONException {
        String expression = json.optString("expression");
        String tag = json.optString("tag");

        return new JsonRequest(expression, tag);
    }

    public static List<JsonRequest> fromJson(String json) throws JSONException {
        List<JsonRequest> requests = new ArrayList<>();
        JSONTokener jsonTokener = new JSONTokener(json);
        Object value = jsonTokener.nextValue();
        if (value instanceof JSONObject) {
            requests.add(fromJson((JSONObject) value));
        } else if (value instanceof JSONArray) {
            JSONArray array = (JSONArray) value;
            for (int i = 0; i < array.length(); i++) {
                Object object = array.get(i);
                if (object instanceof JSONObject) {
                    requests.add(fromJson((JSONObject) object));
                }
            }
        } else {
            throw new IllegalArgumentException("No JSON object or array found");
        }

        return requests;
    }
}
