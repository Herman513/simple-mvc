package org.simplemvc.web.servlet;

import com.alibaba.fastjson.JSON;
import org.simplemvc.util.ClassUtils;
import org.simplemvc.util.ConfigUtils;
import org.simplemvc.util.StringUtils;
import org.simplemvc.web.bind.annotation.Controller;
import org.simplemvc.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：
 */
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private static final long serialVersionUID = 1L;
    private static Routers routers = new Routers();

    public DispatcherServlet() {
        initRouteMapping();
    }

    public void init() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getServletPath();
        Route route=routers.getRoute(path);
        if(route==null){
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", 404);
            resp.setHeader("Content-type", "application/json;charset=UTF-8");
            resp.getWriter().write(JSON.toJSONString(result));
        }else {
            try {
                Object controller = route.getController();
                Method method=route.getAction();
                Object result = method.invoke(controller, new Object[]{req, resp});
                resp.setHeader("Content-type", "application/json;charset=UTF-8");
                resp.getWriter().write(JSON.toJSONString(result));
            } catch (Exception e) {
                logger.error("Error raised in DispatcherServlet.", e);
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("status", 500);
                result.put("message", e.getMessage());
                resp.setHeader("Content-type", "application/json;charset=UTF-8");
                resp.getWriter().write(JSON.toJSONString(result));
            }
        }
    }

    private void initRouteMapping() {
        logger.info("==================start initRouteMapping================");
        ArrayList<Object> controllers = new ArrayList<>();
        ClassUtils.getClassByAnnoation(Controller.class).forEach(controller -> {
            try {
                controllers.add(controller.newInstance());
                logger.info("new instance:" + controller.getName());
            } catch (InstantiationException e) {
                logger.warn("failed to new instance:" + controller.getName(), e);
            } catch (IllegalAccessException e) {
                logger.warn("failed to new instance:" + controller.getName(), e);
            }
        });
        try {
            for (Object controller : controllers) {
                Class<?> clazz = controller.getClass();
                String basePath = clazz.getAnnotation(RequestMapping.class).value();
                List<Method> methods = ClassUtils.getMethodsByAnnoation(clazz, RequestMapping.class);
                for (Method method : methods) {
                    String path = basePath + method.getAnnotation(RequestMapping.class).value();
                    routers.addRoute(new Route(path, method, controller));
                }
            }
        } catch (Exception e) {
            //
        }
        logger.info("==================finish initRouteMapping================");
    }
}
