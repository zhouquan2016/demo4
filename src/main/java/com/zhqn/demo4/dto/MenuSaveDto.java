package com.zhqn.demo4.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: MenuSaveDto
 * Author:   zhouquan3
 * Date:     2021/4/28 22:34
 * Description: 菜单新增dto
 * @author zhouquan3
 */
@Data
public class MenuSaveDto {

    @Pattern(regexp = "^/[/\\w_-]{1,100}$", message = "路由路径不合法")
    private String path;

    @NotEmpty(message = "菜单名称不能为空")
    @Pattern(regexp = "^[\\w_-|(\\u4e00-\\u9fa5)]{1,10}$", message = "菜单名称不合法")
    private String name;

    private String desc;

    private Long parentId;
    /**
     * 是否叶子节点
     */
    @NotNull(message = "是否叶子节点不能为空")
    private Boolean isLeaf;
}
