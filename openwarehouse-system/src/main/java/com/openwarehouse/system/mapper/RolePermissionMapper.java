package com.openwarehouse.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openwarehouse.system.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关联Mapper接口
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
