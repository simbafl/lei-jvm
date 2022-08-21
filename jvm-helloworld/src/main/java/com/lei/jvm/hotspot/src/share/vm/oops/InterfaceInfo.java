package com.lei.jvm.hotspot.src.share.vm.oops;

import lombok.Data;

/**
 * Created By lei
 * 2022/5/2
 *
 */


@Data
public class InterfaceInfo {

    private int constantPoolIndex;

    private String interfaceName;

    public InterfaceInfo(int index, String name) {
        this.constantPoolIndex = index;
        this.interfaceName = name;
    }
}