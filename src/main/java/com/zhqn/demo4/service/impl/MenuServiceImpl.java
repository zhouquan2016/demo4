package com.zhqn.demo4.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.zhqn.demo4.dao.MenuDao;
import com.zhqn.demo4.dto.MenuSaveDto;
import com.zhqn.demo4.entity.Menu;
import com.zhqn.demo4.ex.BaseException;
import com.zhqn.demo4.service.MenuService;
import com.zhqn.demo4.struct.MenuStruct;
import com.zhqn.demo4.utils.ExceptionUtils;
import com.zhqn.demo4.vo.MenuDisplayVo;
import com.zhqn.demo4.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 菜单(Menu)表服务实现类
 *
 * @author makejava
 * @since 2021-04-28 17:33:25
 */
@Service("menuService")
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Resource
    MenuStruct menuStruct;

    @Override
    public Menu findById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return menuDao.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(MenuSaveDto menu) {
        Menu saveMenu = new Menu();
        saveMenu.setCreateTime(LocalDateTime.now());
        saveMenu.setUpdateTime(LocalDateTime.now());
        saveMenu.setDept("");
        if (!menu.getIsLeaf()) {
            saveMenu.setPath(null);
        }
        BeanUtils.copyProperties(menu, saveMenu);
        String parentDept = "";
        ExceptionUtils.assertThat(!this.exists(saveMenu), "菜单名称已存在");
        if (Objects.isNull(menu.getParentId())) {
            saveMenu.setDepth(1);
            saveMenu.setIndex(1);
        }else {
            Menu parent = menuDao.findById(menu.getParentId())
                    .orElseThrow(() -> new BaseException("父菜单不存在"));
            ExceptionUtils.assertThat(!parent.getIsLeaf(), "父菜单是叶子节点");
            saveMenu.setParentId(parent.getParentId());
            saveMenu.setDepth(parent.getDepth() + 1);
            List<Menu> children = menuDao.findByParentId(parent.getId());
            if (CollectionUtil.isEmpty(children)) {
                children = Collections.singletonList(saveMenu);
            }
            saveMenu.setIndex(children.size());
            parentDept = parent.getDept();
        }
        menuDao.save(saveMenu);
        if (StrUtil.isNotBlank(parentDept)) {
            saveMenu.setDept(parentDept + saveMenu.getDept());
        }

    }

    @Override
    public boolean exists(Menu menu) {
        if (!menu.getIsLeaf()) {
            return false;
        }
        Menu findMenu = menuDao.findByName(menu.getName());
        return Objects.nonNull(findMenu);
    }

    @Override
    public MenuDisplayVo list() {
        List<Menu> menus = menuDao.findAll(Sort.sort(Menu.class).by(Menu::getDept).ascending());
        List<MenuVo> header = getChildren(menus, 0,null);
        MenuDisplayVo menuDisplayVo = new MenuDisplayVo();
        menuDisplayVo.setHeader(header);
        if (CollectionUtil.isNotEmpty(header)) {
            header.get(1).setActive(true);
            menuDisplayVo.setActiveId(header.get(1).getId());
        }
        return menuDisplayVo;
    }

    private List<MenuVo> getChildren(List<Menu> menus, int index, Menu parent) {
        List<MenuVo> list = new ArrayList<>();
        for (int i = index; i < menus.size(); i++) {
            Menu menu = menus.get(i);
            if (Objects.isNull(parent) && Objects.nonNull(menu.getParentId())) {
                continue;
            }else if (Objects.nonNull(parent) && !parent.getId().equals(menu.getParentId())) {
                continue;
            }
            MenuVo menuVo = menuStruct.transfer2vo(menu);
            list.add(menuVo);
            menuVo.setChildren(getChildren(menus,index + 1, menu));
        }
        return list;
    }

    @Override
    public List<MenuVo> findByParentId(long parentId) {
        List<Menu> menus = menuDao.findByParentId(parentId);
        return menuStruct.transfer2vo(menus);
    }

    @Override
    public List<MenuVo> list(long parentId) {
        return findByParentId(parentId);
    }

    @Override
    public List<MenuVo> parents(String dept) {
        if (StrUtil.isEmpty(dept)) {
            return null;
        }
        return menuStruct.transfer2vo(menuDao.parents(dept));
    }
}