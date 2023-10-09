package com.snykta.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;


/**
 * 用户信息表
 *
 * 表结构对应 Dto
 *
 * @author Snykta
 * @date 2023-09-27
 */
@Data
@ApiModel(value = "用户信息表")
public class SysUserDto implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
    * 手机号
    */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;


    /**
    * 用户昵称
    */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;


    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    private String password;


    /**
    * 帐号状态（Y正常 N停用）
    */
    @ApiModelProperty(value = "帐号状态（Y正常 N停用）")
    private String statusCode;


    /**
    * 创建人Id
    */
    @ApiModelProperty(value = "创建人Id")
    private Long createId;


    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    /**
    * 创建人姓名
    */
    @ApiModelProperty(value = "创建人姓名")
    private String createName;


    /**
    * 更新人Id
    */
    @ApiModelProperty(value = "更新人Id")
    private Long updateId;


    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    /**
    * 更新人姓名
    */
    @ApiModelProperty(value = "更新人姓名")
    private String updateName;


    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remarkInfo;


}