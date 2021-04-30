package com.zhqn.demo4.struct;

import com.zhqn.demo4.entity.Menu;
import com.zhqn.demo4.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collections;
import java.util.List;

/**
 * FileName: MenuStruct
 * Date:     2021/4/29 18:05
 * Description:
 * @author zhouquan3
 */
@Mapper(componentModel = "spring", imports = {Collections.class})
public interface MenuStruct {

    /**
     * menu to vo
     * @param menu 菜单
     * @return 菜单vo
     */
    @Mappings({
            @Mapping(target = "children", expression = "java(Collections.emptyList())")
    })
    MenuVo transfer2vo(Menu menu);

    /**
     * menu to vo
     * @param menuList list
     * @return vo
     */
    List<MenuVo> transfer2vo(List<Menu> menuList);
}
