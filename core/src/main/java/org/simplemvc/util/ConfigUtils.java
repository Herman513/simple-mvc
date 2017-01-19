package org.simplemvc.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：读取配置文件
 */
public class ConfigUtils {
    private static Properties config = new Properties();

    static {//run those code when load this class, make sure those code just run once
        List<File> files = FileUtils.findFiles(".properties");//只处理properties类型的配置文件
        Properties prop;
        for (File file : files) {
            prop = new Properties();
            try {
                prop.load(new FileReader(file));
                config.putAll(prop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void init() {

    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}
