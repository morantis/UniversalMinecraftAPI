package com.koenv.jsonapi;

import com.koenv.jsonapi.http.model.JsonSerializable;
import com.koenv.jsonapi.methods.*;
import com.koenv.jsonapi.serializer.SerializerManager;
import com.koenv.jsonapi.util.json.JSONArray;
import com.koenv.jsonapi.util.json.JSONObject;

@APINamespace("jsonapi")
public class JSONAPIMethods {
    @APIMethod
    public static String getInvoker(Invoker invoker) {
        return invoker.toString();
    }

    @APIMethod
    public static String getVersion() {
        return JSONAPI.getInstance().getProvider().getJSONAPIVersion();
    }

    @APIMethod
    public static String getPlatform() {
        return JSONAPI.getInstance().getProvider().getPlatform();
    }

    @APIMethod
    public static String getPlatformVersion() {
        return JSONAPI.getInstance().getProvider().getPlatformVersion();
    }

    @APIMethod
    public static Methods listMethods() {
        JSONObject json = new JSONObject();

        MethodInvoker methodInvoker = JSONAPI.getInstance().getMethodInvoker();

        JSONArray namespaces = new JSONArray();
        methodInvoker.getNamespaces().values().forEach(map -> map.values().forEach(method -> namespaces.put(MethodUtils.getMethodDeclaration(method))));

        JSONArray classes = new JSONArray();
        methodInvoker.getClasses().values().forEach(map -> map.values().forEach(method -> classes.put(MethodUtils.getMethodDeclaration(method))));

        json.put("namespaces", namespaces);
        json.put("classes", classes);

        return new Methods(json);
    }

    public static class Methods implements JsonSerializable {
        private JSONObject json;

        public Methods(JSONObject json) {
            this.json = json;
        }

        @Override
        public JSONObject toJson(SerializerManager serializerManager) {
            return json;
        }
    }
}
