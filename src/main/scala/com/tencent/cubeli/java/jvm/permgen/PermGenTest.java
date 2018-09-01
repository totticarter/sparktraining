package com.tencent.cubeli.java.jvm.permgen;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyong on 01/09/2018.
 */
public class PermGenTest {

    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.tencent.cubeli.java.jvm.permgen.Test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
