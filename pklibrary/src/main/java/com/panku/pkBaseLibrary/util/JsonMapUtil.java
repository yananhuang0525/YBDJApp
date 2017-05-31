package com.panku.pkBaseLibrary.util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/13.
 */

public class JsonMapUtil {
    /**
     * map转成json字符串
     *
     * @param map
     * @return
     */
    public static String simpleMapToJsonStr(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "null";
        }
        String jsonStr = "{";
        Set<?> keySet = map.keySet();
        for (Object key : keySet) {
            jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
        }
        jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
        jsonStr += "}";
        return jsonStr;
    }
    public static String simpleMapToStr(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "null";
        }
//        String jsonStr = "\"";
        String jsonStr = "";
        Set<?> keySet = map.keySet();
        for (Object key : keySet) {
            jsonStr +=  key + ":" + map.get(key) + ",";
        }
        jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
//        jsonStr += "\"";
        jsonStr += "";
        return jsonStr;
    }
    /**
     * Json 转成 Map<>
     * @param jsonStr
     * @return
     */
    public static Map<String, String> getMapForJson(String jsonStr){
        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter= jsonObject.keys();
            String key;
            String value ;
            Map<String, String> valueMap = new HashMap<String, String>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key).toString();
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}
