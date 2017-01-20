package org.simplemvc.test.controller;

import org.simplemvc.web.bind.annotation.Controller;
import org.simplemvc.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/index")
    public Object index(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "index method is invorked!");
        return result;
    }

    @RequestMapping("/welcome")
    public Object welcome(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "Welcome to simple-mvc !");
        return result;
    }
}
