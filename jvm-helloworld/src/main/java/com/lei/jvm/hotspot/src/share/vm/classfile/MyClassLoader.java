package com.lei.jvm.hotspot.src.share.vm.classfile;

import cn.hutool.core.io.FileUtil;
import com.lei.jvm.hotspot.src.share.vm.oops.InstanceKlass;
import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Created By lei
 * 2022/5/2
 *
 * 根类加载器
 */


@Data
public class MyClassLoader {

    public static final String SUFFIX = ".class";

    /**
     *  该类加载器的加载路径
     *      多个路径以分号分隔
     *      只有一个的话，分号可省略
     *
     *  注意路径后面的斜杠不可丢
     */
    private static String searchPath = "/Users/leifeng/study/github/lei-jvm/jvm_helloworld/target/classes/";

    /**
     *  用于存储该类加载器加载的所有类
     */
    private static Map<String, InstanceKlass> classLoaderData = new HashMap<>();

    /**
     *  main函数所在的类在此保存一份引用，方便快速定位到
     */
    private static InstanceKlass mainKlass = null;

    public static InstanceKlass getMainKlass() {
        return mainKlass;
    }

    public static void setMainKlass(InstanceKlass mainKlass) {
        MyClassLoader.mainKlass = mainKlass;
    }

    public static InstanceKlass loadKlass(String name) {
        return loadKlass(name, true);
    }

    public static InstanceKlass loadKlass(String name, Boolean resolve) {
        InstanceKlass klass = findLoadedKlass(name);
        if (null != klass) {
            return klass;
        }

        klass = readAndParse(name);

        if (resolve) {
            resolveKlass();
        }

        return klass;
    }

    private static InstanceKlass readAndParse(String name) {
        String tmpName = name.replace('.', '/');
        String filePath = searchPath + tmpName + SUFFIX;

        // 读取字节码文件
        byte[] content = FileUtil.readBytes(new File(filePath));

        // 解析字节码文件
        InstanceKlass klass = ClassFileParser.parseClassFile(content);

        // 存入
        classLoaderData.put(name, klass);

        return klass;
    }

    private static void resolveKlass() {
    }

    public static InstanceKlass findLoadedKlass(String name) {
        return classLoaderData.get(name);
    }

    public static InstanceKlass loadMainKlass(String name) {
        /*
        Hotspot源码通过AppClassLoader将所有类加载到一个容器，然后再去查找main方法
         */
        if (null != mainKlass) {
            return mainKlass;
        }

        return loadKlass(name);
    }
}
