package com.snykta.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.snykta.tools.web.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


import java.util.Date;

/**
 * 用户信息表
 *
 * 表结构实体类
 *
 * @author Snykta
 * @date 2023-09-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField("status_code")
    private String statusCode;

    /**
     * 创建人Id
     */
    @TableField("create_id")
    private Long createId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人姓名
     */
    @TableField("create_name")
    private String createName;

    /**
     * 更新人Id
     */
    @TableField("update_id")
    private Long updateId;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新人姓名
     */
    @TableField("update_name")
    private String updateName;

    /**
     * 备注
     */
    @TableField("remark_info")
    private String remarkInfo;

}