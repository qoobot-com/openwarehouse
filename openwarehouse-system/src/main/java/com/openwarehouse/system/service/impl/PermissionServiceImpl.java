package com.openwarehouse.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.system.entity.Permission;
import com.openwarehouse.system.mapper.PermissionMapper;
import com.openwarehouse.system.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限Service实现类
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {
}
