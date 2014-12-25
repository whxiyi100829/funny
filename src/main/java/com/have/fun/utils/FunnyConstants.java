package com.have.fun.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/24 Time: 00:55
 * </pre>
 */
public class FunnyConstants {
    public static final Splitter SPLITTER  = Splitter.on("#").trimResults();
    public static final String   ITEM_FILE = "item.txt";
    public static final Joiner   JOINER   = Joiner.on("#");
}
