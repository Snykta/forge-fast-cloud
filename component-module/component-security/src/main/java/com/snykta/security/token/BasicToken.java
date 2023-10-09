package com.snykta.security.token;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 用户基础数据token映射类
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
    private  String getUserName;

    /**
     * 用户编号
     */
    private  String getUserNumber;

    /**
     * 权限编码集合
     */
    private List<String> getRightCodeList;

    /**
     * 角色编码集合
     */
    private  List<String> getRoleCodeList;


}

