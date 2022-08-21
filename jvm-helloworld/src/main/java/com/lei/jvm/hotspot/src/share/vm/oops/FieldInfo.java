package com.lei.jvm.hotspot.src.share.vm.oops;

import lombok.Data;

/**
 * Created By lei
 * 2022/5/2
 *
 */


@Data
public class FieldInfo {

    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;

    private CodeAttributeInfo[] attributes;



}
