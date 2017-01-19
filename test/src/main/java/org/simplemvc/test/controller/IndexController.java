package org.simplemvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：
 */
public class IndexController {
    public Object index(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "index method is invorked!");
        return result;
    }

    public Object welcome(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "Welcome to jw's world!");
        return result;
    }
}
