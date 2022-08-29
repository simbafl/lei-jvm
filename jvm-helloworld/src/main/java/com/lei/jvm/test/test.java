package com.lei.jvm.test;

import com.lei.jvm.hotspot.src.share.tools.DataTranslate;

import java.util.Arrays;

/**
 * @author leifeng
 * @for testp
 * @since 2022/8/29
 */
public class test {
    public static void main(String[] args) {
        byte[] b = new byte[]{'5', 'p', 'c', 'f'};
        String s = DataTranslate.bytesToHexFun1(b);
        System.out.println("s = " + s);
        String ss = DataTranslate.bytesToHexFun2(b);
        System.out.println("ss = " + ss);

    }

}
