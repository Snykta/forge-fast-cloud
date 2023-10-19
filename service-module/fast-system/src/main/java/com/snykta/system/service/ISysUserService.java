package com.snykta.system.service;


import com.snykta.system.dto.SearchUserDto;
import com.snykta.system.dto.SysUserDto;
import com.snykta.tools.web.page.PageDto;

/**
 * 用户信息表
 *
 * @author Snykta
 * @date 2023-09-27
 */
public interface ISysUserService {

    /**
     * 分页查询用户
     * @param searchUserDto
     * @return
     */
    PageDto<SysUserDto> queryPage(SearchUserDto searchUserDto);
}