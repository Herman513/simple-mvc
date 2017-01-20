package org.simplemvc.test.controller;

import org.simplemvc.util.ClassUtils;
import org.simplemvc.web.bind.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：
 */
@Controller
public class IndexController {
    public Object index(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        List<String> s = ClassUtils.getClassNames();
        List<Class> testAnno = ClassUtils.getClassByAnnoation(Controller.class);
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
