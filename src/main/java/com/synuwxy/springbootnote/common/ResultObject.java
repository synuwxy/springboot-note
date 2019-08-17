package com.synuwxy.springbootnote.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxy
 * Create by 2019.08.17
 *
 * desc:
 * 默认的响应数据模板
 */
public class ResultObject {

    // 返回的数据码
    private static String CODE = "code";
    // 返回的数据内容
    private static String DATA = "data";

    public static Map<String, Object> newInstance(String code, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(ResultObject.CODE,code);
        map.put(ResultObject.DATA,data);
        return map;
    }
}