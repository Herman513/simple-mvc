package org.simplemvc.web.servlet;

import org.simplemvc.util.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by wuyuhuan on 2017/1/20.
 * Usage：路由匹配
 */
public class RouteMatcher {
    private static Routers routers=new Routers();
    static {
        List<File> files = FileUtils.findClassPathFiles(".class");
        for(File file:files){

        }
    }

    public RouteMatcher() {
    }
}
