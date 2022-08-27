package com.lei.jvm.jdk;

import com.lei.jvm.hotspot.src.share.vm.runtime.JavaThread;
import com.lei.jvm.hotspot.src.share.vm.classfile.MyClassLoader;
import com.lei.jvm.hotspot.src.share.vm.oops.InstanceKlass;
import com.lei.jvm.hotspot.src.share.vm.oops.MethodInfo;
import com.lei.jvm.hotspot.src.share.vm.prims.JavaNativeInterface;
import com.lei.jvm.hotspot.src.share.vm.runtime.Threads;


/**
 * Created By lei
 * 2022/5/2
 *
 * 通过对字节码的解析，来调用main方法
 * 首先要编译产生字节码，然后指定对应的地址
 */


public class Main {

    public static void main(String[] args) {
        startJVM();
    }

    public static void startJVM() {
        // 模拟AppClassLoader加载main函数所在的类
        // 通过该类的全限定名获取存储该类的class文件
        InstanceKlass mainKlass = MyClassLoader.loadMainKlass("com.lei.jvm.example.HelloWorld");
        // 找到main方法
        MethodInfo mainMethod = JavaNativeInterface.getMethodID(mainKlass,"main", "([Ljava/lang/String;)V");

        // 创建线程
        JavaThread thread = new JavaThread();

        Threads.getThreadList().add(thread);
        Threads.setCurrentThread(thread);
        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }
}
