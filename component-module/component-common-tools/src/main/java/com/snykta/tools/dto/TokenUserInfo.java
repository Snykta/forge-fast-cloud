package com.snykta.tools.dto;



import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 用户基础数据token映射类
 * 包含：用户信息、用户角色、用户权限
 */
@Data
public class TokenUserInfo implements Serializable {

    /**
     * id
     */
    private Long id;


    /**
     * 手机号
     */
    private String phoneNumber;


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;


    /**
     * 帐号状态（0正常 1停用）
     */
    private String statusCode;

    /**
     * 权限编码集合
     */
    private List<String> rightCodeList;


    /**
     * 角色编码集合
     */
    private List<String> roleCodeList;


}
