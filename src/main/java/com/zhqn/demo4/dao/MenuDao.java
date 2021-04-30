package com.zhqn.demo4.dao;

import com.zhqn.demo4.entity.Menu;
import com.zhqn.demo4.vo.MenuVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 菜单(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-28 17:33:25
 */
public interface MenuDao extends JpaRepository<Menu, Long> {

    Menu findByName(String name);

    List<Menu> findByDepthIn(List<Integer> depts);

    List<Menu> findByParentId(Long parentId);

    @Query("from Menu where :dept like concat(dept, ':%')  or dept = :dept order by depth")
    List<Menu> parents(String dept);
}