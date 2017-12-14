package com.red.dargon.nsbaselibrary.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 杨观回 on 2015/11/12 0012
 *
 * map操作工具类，json转map
 */
public class MapUtils {

    public static HashMap<String, Object> jsonToMap(JSONObject jsonObject) {
        if (jsonObject==null)return new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                map.put(key, jsonObject.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String,Object> propertiesToMap(Properties properties) {
        if(properties == null) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(properties);
        return jsonToMap(jsonObject);
    }
}
