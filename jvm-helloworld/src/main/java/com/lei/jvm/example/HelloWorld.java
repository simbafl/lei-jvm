package com.lei.jvm.example;

/**
 * Created By lei
 * 2022/5/1
 */

public class HelloWorld {
    /**
     * 字节码执行流程【Jclasslib】：
     *
     * 0 getstatic #2 <java/lang/System.out : Ljava/io/PrintStream;>
     *     取静态属性，压入栈
     * 3 ldc #3 <hello, world>
     *     从运行时常量池取出字符串对象，然后压入栈
     * 5 invokevirtual #4 <java/io/PrintStream.println : (Ljava/lang/String;)V>
     *     构建运行环境：创建println栈帧、参数出栈、this指针赋值
     *     执行方法
     * 8 return
     *     清理栈帧（如果是最后一个顺便清理虚拟机栈）
     */
    public static void main(String[] args) {
        System.out.println("lei, hello world!");
    }
}