package org.simplemvc.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：读取配置文件相关工具类
 */
public class FileUtils {
    //获取classpath下所有的以fileExtension结尾的文件
    public static List<File> findFiles(final String fileExtension) {
        List<File> files = new LinkedList<>();
        try {
            URL url = FileUtils.class.getResource("/");//获取包的根路径
            if (url == null)
                return files;
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                File dir = new File(filePath);
                if (!dir.exists() || !dir.isDirectory())
                    return files;
                File[] targetFiles = dir.listFiles(file -> file.getName().endsWith(fileExtension));
                if (targetFiles != null && targetFiles.length > 0) {
                    files.addAll(Arrays.asList(targetFiles));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
