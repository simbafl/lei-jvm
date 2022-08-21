package com.lei.jvm.hotspot.src.share.vm.runtime;

import com.lei.jvm.hotspot.src.share.vm.oops.MethodInfo;
import lombok.Data;

/**
 * Created By lei
 * 2022/5/2
 *
 */


@Data
public class JavaVFrame extends VFrame {

    private StackValueCollection locals;

    private StackValueCollection stack = new StackValueCollection();

    private MethodInfo ownerMethod;

    public JavaVFrame(int maxLocals) {
        locals = new StackValueCollection(maxLocals);
    }

    public JavaVFrame(int maxLocals, MethodInfo methodInfo) {
        locals = new StackValueCollection(maxLocals);

        ownerMethod = methodInfo;
    }
}
