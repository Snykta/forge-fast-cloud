package com.snykta.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snykta.basic.web.exception.ServiceException;
import com.snykta.system.mapper.SysUserMapper;
import com.snykta.system.dto.SysUserDto;
import com.snykta.system.entity.SysUserEntity;
import com.snykta.system.service.ISysUserService;
import com.snykta.tools.dto.TokenUserInfo;
import com.snykta.tools.utils.CyConvertUtil;
import com.snykta.tools.utils.CyEncryptUtil;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.utils.CyStrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snykta.basic.web.web.service.BaseService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户信息表
 *
 * 1、禁止删除 @Transactional(readOnly = true)
 * 2、禁止继承或者实现mybatisPlus自带的crud基类service
 *
 * 增删改的操作方法上需要加上 @Transactional(rollbackFor = Exception.class)
 *
 * @author Snykta
 * @since 2023-09-27
 */
@Slf4j
@Service("sysUserService")
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseService implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }


    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public TokenUserInfo doLogin(String phoneNumber, String password) {
        if (CyStrUtil.isEmpty(phoneNumber)) {
            throw new ServiceException("请输入登录手机号");
        }
        if (CyStrUtil.isEmpty(password)) {
            throw new ServiceException("请输入登录密码");
        }
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getPhoneNumber, phoneNumber));
        if (CyObjUtil.isNull(sysUserEntity)) {
            throw new ServiceException("账户或密码不正确");
        }
        if (!CyStrUtil.equals(CyEncryptUtil.decryptAes(sysUserEntity.getPassword()), password)) {
            throw new ServiceException("账户或密码不正确");
        }
        TokenUserInfo tokenUserInfo = new TokenUserInfo();
        tokenUserInfo.setId(sysUserEntity.getId());
        tokenUserInfo.setPhoneNumber(sysUserEntity.getPhoneNumber());
        tokenUserInfo.setNickName(sysUserEntity.getNickName());
        tokenUserInfo.setPassword(sysUserEntity.getPassword());
        tokenUserInfo.setStatusCode(sysUserEntity.getStatusCode());
        // TODO 添加权限、角色

        return tokenUserInfo;
    }

    /**
     * 注册
     * @param sysUserDto
     */
    @Override
    public void doRegister(SysUserDto sysUserDto) {
        if (CyObjUtil.isNull(sysUserDto)) {
            throw new ServiceException("注册信息不能为空");
        }
        if (CyStrUtil.isEmpty(sysUserDto.getPhoneNumber())) {
            throw new ServiceException("手机号不能为空");
        }
        if (CyStrUtil.isEmpty(sysUserDto.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        if (CyStrUtil.isEmpty(sysUserDto.getNickName())) {
            throw new ServiceException("昵称不能为空");
        }
        boolean existsFlag = sysUserMapper.exists(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getPhoneNumber, sysUserDto.getPhoneNumber()));
        if (existsFlag) {
            throw new ServiceException("注册账号已存在");
        }
        sysUserDto.setId(null);
        sysUserMapper.insert(CyConvertUtil.convertToEntity(sysUserDto, SysUserEntity.class));
    }


}