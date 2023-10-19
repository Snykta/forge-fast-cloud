package com.snykta.security.token;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 用户基础数据token映射基础类
 * 包含：用户信息、用户角色、用户权限
 */
@Data
public class BasicToken implements Serializable {

    /**
     * 用户ID主键
     */
    private Long userId;

    /**
     * 用户中文名称
     */
    private  String userName;

    /**
     * 用户编号（手机号、工号、微信号、QQ号）等唯一编码都可以
     */
    private  String userNumber;

    /**
     * 权限编码集合
     */
    private List<String> rightCodeList;

    /**
     * 角色编码集合
     */
    private  List<String> roleCodeList;


}

