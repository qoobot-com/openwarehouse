package com.qoobot.openwarehouse.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.system.entity.Role;
import com.qoobot.openwarehouse.system.mapper.RoleMapper;
import com.qoobot.openwarehouse.system.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
