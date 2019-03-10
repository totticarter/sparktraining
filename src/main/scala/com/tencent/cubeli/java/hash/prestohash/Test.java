package com.tencent.cubeli.java.hash.prestohash;

/**
 * Created by liyong on 2019/2/12.
 */
public class Test {

    public static void main(String[] args) {

        long hash = rotateLeft(4186301 * 0xC2B2AE3D27D4EB4FL, 31) * 0x9E3779B185EBCA87L;
        System.out.println(hash);
    }

    public static long rotateLeft(long i, int distance) {
        return (i << distance) | (i >>> -distance);
    }
}
