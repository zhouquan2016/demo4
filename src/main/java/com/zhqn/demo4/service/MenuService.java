package com.zhqn.demo4.service;

import com.zhqn.demo4.dto.MenuSaveDto;
import com.zhqn.demo4.entity.Menu;
import com.zhqn.demo4.vo.MenuDisplayVo;
import com.zhqn.demo4.vo.MenuVo;

import java.util.List;

/**
 * 菜单(Menu)表服务接口
 *
 * @author makejava
 * @since 2021-04-28 17:33:25
 */
public interface MenuService {

    /**
     * 根据id查找
     * @param id id
     * @return entity
     */
    Menu findById(Long id);

    void save(MenuSaveDto menu);

    boolean exists(Menu saveMenu);

    MenuDisplayVo list();

    List<MenuVo> findByParentId(long parentId);

    List<MenuVo> list(long parentId);

    List<MenuVo> parents(String dept);
}