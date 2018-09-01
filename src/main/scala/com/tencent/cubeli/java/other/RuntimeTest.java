package com.tencent.cubeli.java.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by liyong on 10/06/2018.
 */
public class RuntimeTest {

    public static void main(String[] args) {

        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("mkdir tmp");
            run.exec("cp sparkproj.iws tmp");
//            InputStream in = process.getInputStream();
//            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
//            StringBuffer out = new StringBuffer();
//            byte[] b = new byte[8192];
//            for (int n; (n = in.read(b)) != -1; ) {
//                out.append(new String(b, 0, n));
//            }
//            System.out.println("job result [" + out.toString() + "]");
//            in.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
