package org.simplemvc.web.servlet;

import java.lang.reflect.Method;

/**
 * Created by wuyuhuan on 2017/1/20.
 * Usage：路由
 */
public class Route {
    private String path;
    private Method action;//路由所在方法
    private Object controller;//路由所在控制器

    public Route(String path, Method action, Object controller) {
        this.path = path;
        this.action = action;
        this.controller = controller;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    @Override
    public String toString() {
        return "Route{" +
                "path='" + path + '\'' +
                ", action=" + action.getName() +
                ", controller=" + controller.getClass().getName() +
                '}';
    }
}
