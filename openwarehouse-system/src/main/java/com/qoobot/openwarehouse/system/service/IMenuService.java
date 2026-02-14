package com.qoobot.openwarehouse.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qoobot.openwarehouse.system.entity.Menu;

import java.util.List;

/**
 * 菜单服务接口
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 获取菜单树
     */
    List<Menu> getMenuTree();
}
