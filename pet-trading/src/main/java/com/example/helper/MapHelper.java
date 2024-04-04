package com.example.helper;

import com.example.config.Constants;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MapHelper {

    public static void putIfNotNull(Map<String, Object> map, String key, Object value) {
        if (value != null) {
            map.put(key, value);
        }
    }

    public static void putIfHasLength(Map<String, Object> map, String key, String value) {
        if (StringUtils.hasLength(value)) {
            map.put(key, value);
        }
    }

    public static void putPagination(Map<String, Object> map, Integer page, Integer size) {
        if (page == null || size == null) {
            return;
        }
        map.put("min", (page - 1) * size);
        map.put("max", size);
    }

    public static void flagAndMessage(Map<String, Object> map, int flag, String message) {
        map.put("flag", flag);
        map.put("msg", message);
    }

    public static void successAndMessage(Map<String, Object> map, String message) {
        flagAndMessage(map, Constants.FLAG_SUCCESS, message);
    }

    public static void failAndMessage(Map<String, Object> map, String message) {
        flagAndMessage(map, Constants.FLAG_FAIL, message);
    }
}
