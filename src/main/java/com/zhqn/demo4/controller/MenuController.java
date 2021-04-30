package com.zhqn.demo4.controller;

import com.zhqn.demo4.dto.MenuSaveDto;
import com.zhqn.demo4.entity.Menu;
import com.zhqn.demo4.service.MenuService;
import com.zhqn.demo4.vo.MenuDisplayVo;
import com.zhqn.demo4.vo.MenuVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单(Menu)表控制层
 *
 * @author makejava
 * @since 2021-04-28 17:33:25
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Menu selectOne(Long id) {
        return this.menuService.findById(id);
    }

    @PostMapping("/")
    public String save(@RequestBody @Validated MenuSaveDto menuSaveDto) {
        menuService.save(menuSaveDto);
        return "ok";
    }

    @GetMapping("/list")
    public MenuDisplayVo list() {
        return menuService.list();
    }

    @GetMapping("/children")
    public List<MenuVo> list(long parentId) {
        return menuService.list(parentId);
    }

    @GetMapping("/parents")
    public List<MenuVo> parents(String dept) {
        return menuService.parents(dept);
    }
}