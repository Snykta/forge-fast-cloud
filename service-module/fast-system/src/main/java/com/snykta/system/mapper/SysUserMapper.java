package com.snykta.system.mapper;


import com.snykta.system.entity.SysUserEntity;
import org.springframework.stereotype.Repository;
import com.snykta.mybatis.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 *
 * @author Snykta
 * @date 2023-09-27
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapperPlus<SysUserEntity> {
	
}