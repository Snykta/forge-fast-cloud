package com.snykta.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snykta.starter.mybatis.plus.page.PageRequest;
import com.snykta.system.dto.SearchUserDto;
import com.snykta.system.mapper.SysUserMapper;
import com.snykta.system.dto.SysUserDto;
import com.snykta.system.entity.SysUserEntity;
import com.snykta.system.service.ISysUserService;
import com.snykta.starter.tools.utils.CyConvertUtil;
import com.snykta.starter.tools.utils.CyStrUtil;
import com.snykta.starter.tools.web.page.PageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snykta.starter.basic.web.web.service.BaseService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户信息表
 *
 * 1、禁止删除 @Transactional(readOnly = true)！！！！增删改的方法上必须加上 @Transactional(rollbackFor = Exception.class)否则将操作失败！！！
 * 2、禁止继承或者实现mybatisPlus自带的crud基类service
 * 3、所有crud操作下沉到Mapper层，service只关心业务
 *
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
     * 单表分页查询
     * @param searchUserDto
     * @return
     */
    @Override
    public PageDto<SysUserDto> queryPage(SearchUserDto searchUserDto) {
        // 分页对象
        Page<SysUserEntity> pageRequest = PageRequest.of(searchUserDto.getPageNum(), searchUserDto.getPageSize());
        // 查询条件
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();

        if (CyStrUtil.isNotEmpty(searchUserDto.getUserName())) {
            queryWrapper.eq(SysUserEntity::getNickName, searchUserDto.getUserName());
        }

        Page<SysUserEntity> resultPage = sysUserMapper.selectPage(pageRequest, queryWrapper);

        return CyConvertUtil.toPageDto(resultPage, SysUserDto.class);
    }

}