package com.lei.jvm.hotspot.src.share.vm.prims;

import com.lei.jvm.hotspot.src.share.vm.oops.CodeAttributeInfo;
import com.lei.jvm.hotspot.src.share.vm.oops.MethodInfo;
import com.lei.jvm.hotspot.src.share.vm.runtime.JavaThread;
import com.lei.jvm.hotspot.src.share.vm.runtime.JavaVFrame;
import com.lei.jvm.hotspot.src.share.vm.runtime.Threads;
import com.lei.jvm.hotspot.src.share.vm.intepreter.BytecodeInterpreter;
import com.lei.jvm.hotspot.src.share.vm.oops.InstanceKlass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created By lei
 * 2022/5/2
 *
 */


public class JavaNativeInterface {

    private final static Logger logger = LoggerFactory.getLogger(JavaNativeInterface.class);

    public static InstanceKlass findClass(String name) {

        return null;
    }

    public static MethodInfo getMethodID(InstanceKlass klass, String name, String descriptorName) {
        MethodInfo[] methods = klass.getMethods();
        System.out.println("klass = " + klass + ", name = " + name + ", methods = " + Arrays.toString(methods));
        for (MethodInfo method:methods) {
            String tmpName = (String) klass.getConstantPool().getDataMap().get(method.getNameIndex());
            String tmpDescriptor = (String) klass.getConstantPool().getDataMap().get(method.getDescriptorIndex());

            if (tmpName.equals(name) && tmpDescriptor.equals(descriptorName)) {
                logger.info("找到了方法: " + name + "#" + descriptorName);

                return method;
            }
        }

        logger.error("没有找到方法: " + name + "#" + descriptorName);

        return null;
    }

    public static void callStaticMethod(MethodInfo method) {
        JavaThread thread = Threads.currentThread();

        if (!method.getAccessFlags().isStatic()) {
            throw new Error("只能调用静态方法");
        }

        CodeAttributeInfo codeAttributeInfo = method.getAttributes()[0];

        // 创建栈帧
        JavaVFrame frame = new JavaVFrame(codeAttributeInfo.getMaxLocals(), method);

        thread.getStack().push(frame);

        logger.info("第 " + thread.getStack().size() + " 个栈帧");

        // 执行任务交给字节码解释器
        BytecodeInterpreter.run(thread, method);
    }

}
