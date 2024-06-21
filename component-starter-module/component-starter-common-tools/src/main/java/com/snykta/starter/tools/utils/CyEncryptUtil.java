package com.snykta.starter.tools.utils;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.snykta.starter.tools.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyEncryptUtil extends SecureUtil {


    /**
     * aes加密 返回加密后的Base64
     * @param strVal
     * @return
     */
    public static String encryptAes(String strVal) {
        try {
            byte[] strValByte = CyStrUtil.bytes(strVal, CharsetUtil.CHARSET_UTF_8);
            AES aes = initAES();
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
            byte[] strValByte = Base64Decoder.decode(strVal);
            AES aes = initAES();
            return aes.decryptStr(strValByte);
        } catch (Exception e) {
            log.error("解密失败.....");
        }
        return null;
    }


    /**
     * 组装AES加密
     * @return
     */
    private static AES initAES() {
        byte[] iv_byte = CyStrUtil.bytes(AuthConstant.iv_salt, CharsetUtil.CHARSET_UTF_8);
        byte[] key_byte = CyStrUtil.bytes(AuthConstant.key_salt, CharsetUtil.CHARSET_UTF_8);
        return new AES(Mode.CTS, Padding.ZeroPadding, key_byte, iv_byte);
    }




}
