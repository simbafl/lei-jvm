package com.lei.jvm.hotspot.src.share.vm.runtime;

import lombok.Data;

import java.util.Stack;

/**
 * Created By lei
 * 2022/5/2
 *
 */


@Data
public class JavaThread extends Thread {

    private Stack<VFrame> stack = new Stack<>();

}
