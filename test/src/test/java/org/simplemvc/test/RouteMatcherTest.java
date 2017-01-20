package org.simplemvc.test;

import org.junit.Test;
import org.simplemvc.util.FileUtils;
import org.simplemvc.web.servlet.RouteMatcher;

import java.io.File;
import java.util.List;

/**
 * Created by wuyuhuan on 2017/1/20.
 * Usage：
 */
public class RouteMatcherTest {
    @Test
    public void matcherTest(){
        RouteMatcher matcher = new RouteMatcher();
        List<File> files = FileUtils.findClassPathFiles(".class");
        for(File file:files){
            System.out.println(file.getName());
        }
    }
}
