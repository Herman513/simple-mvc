package org.simplemvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wuyuhuan on 2017/1/20.
 * Usage：用来扫描设定包下所有class
 */
public class ClassUtils {
    private static Logger logger= LoggerFactory.getLogger(ConfigUtils.class);
    private static List<String> classNames=new ArrayList<>();
    private static List<Class> classObj=new ArrayList<>();
    static {
        String packageScan=ConfigUtils.getProperty("package.scan");
        String rootPath=ClassUtils.class.getResource("/").getPath();
        if(packageScan!=null) {
            List<File> files = FileUtils.findFiles(packageScan.replace('.',File.separatorChar),".class");
            files.forEach(file ->classNames.add(file.getAbsolutePath().replace(rootPath,"").replace(".class","").replace(File.separatorChar,'.')));
        }
        Class clazz;
       for(String className:classNames) {
           try {
               clazz=Class.forName(className);
               classObj.add(clazz);
               logger.info("loading class :"+clazz.getName());
           } catch (ClassNotFoundException e) {
               logger.error("fail load class:"+className,e);
           }
       }
    }

    public static List<String> getClassNames() {
        return classNames;
    }

    public static List<Class> getClassByAnnoation(Class annotation){
        return classObj.stream().filter(clazz->{
            Annotation[] annos = clazz.getDeclaredAnnotations();
            for (Annotation anno : annos) {
                if(anno.annotationType()==annotation)
                    return true;
            }
            return false;
        }).collect(Collectors.toList());
    }
}
