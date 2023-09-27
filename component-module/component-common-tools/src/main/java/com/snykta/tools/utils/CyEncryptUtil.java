package com.snykta.tools.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.snykta.tools.constant.AuthConstant;

public class CyEncryptUtil extends SecureUtil {


    /**
     * aes加密 返回加密后的Base64
     * @param strVal
     * @return
     */
    public static String encryptAes(String strVal) {
        try {
            byte[] strValByte = CyStrUtil.bytes(strVal, CharsetUtil.CHARSET_UTF_8);
            byte[] iv_byte = CyStrUtil.bytes(AuthConstant.iv_salt, CharsetUtil.CHARSET_UTF_8);
            AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, SecureUtil.generateKey("AES"), iv_byte);
            return aes.encryptBase64(strValByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aes解密
     * @param strVal
     * @return
     */
    public static String decryptAes(String strVal) {
        try {
            byte[] strValByte = CyStrUtil.bytes(strVal, CharsetUtil.CHARSET_UTF_8);
            byte[] iv_byte = CyStrUtil.bytes(AuthConstant.iv_salt, CharsetUtil.CHARSET_UTF_8);
            AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, SecureUtil.generateKey("AES"), iv_byte);
            return aes.decryptStr(strValByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





}
