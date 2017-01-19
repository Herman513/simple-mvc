package org.simplemvc.web.servlet;

import com.alibaba.fastjson.JSON;
import org.simplemvc.util.ConfigUtils;
import org.simplemvc.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：
 */
public class DispatcherServlet extends HttpServlet{
    private static  final Logger logger= LoggerFactory.getLogger(DispatcherServlet.class);
    private static final long serialVersionUID=1L;

    public DispatcherServlet() {
    }
    public void init(){

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String appName=req.getSession().getServletContext().getContextPath();// ??
        String path=req.getRequestURI().substring(appName.length());
        logger.info("Request path is "+path);
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        } else if (path.startsWith("/")) {// /index/index => index/index
            path = path.replaceFirst("/", "");
        }
        if (path.isEmpty()) {
            path = "index/index";
        } else if (!path.contains("/")) {
            path += "/index";        // index => index/index
        }
        String[] paths = path.split("/", 2);
        String controllerClazeName = ConfigUtils.getProperty("package.scan") + "." + StringUtils.upperFirst(paths[0])
                + "Controller";
        String methodName = paths[1];

        try {
            Class<?> controllerClaze = Class.forName(controllerClazeName);
            Method method = controllerClaze.getMethod(methodName,
                    new Class<?>[] { HttpServletRequest.class, HttpServletResponse.class });

            Object controller = controllerClaze.newInstance();
            Object result = method.invoke(controller, new Object[] { req, resp });
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
