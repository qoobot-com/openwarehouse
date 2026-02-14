package com.qoobot.openwarehouse.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.system.entity.Menu;
import com.qoobot.openwarehouse.system.mapper.MenuMapper;
import com.qoobot.openwarehouse.system.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务实现
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getMenuTree() {
        // TODO: 实现菜单树构建逻辑
        return this.list();
    }
}
