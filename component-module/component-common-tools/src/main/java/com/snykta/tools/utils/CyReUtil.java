package com.snykta.tools.utils;

import cn.hutool.core.util.ReUtil;

/**
 * 正则
 */
public class CyReUtil extends ReUtil {


    /**
     * 判断是否为合法java包名
     *
     * 例如：com.snykta.controller、com.snykta
     *
     * 合法则返回 true
     *
     * @param packName
     * @return
     */
    public static boolean isValidPackageName(String packName) {
        return contains("^[a-zA-Z_][\\w.]*$", packName);
    }


}
