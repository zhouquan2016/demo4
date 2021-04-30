package com.zhqn.demo4.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单(Menu)实体类
 *
 * @author makejava
 * @since 2021-04-28 17:33:25
 */
@Entity
@Table
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 554788597808743793L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    * 菜单名称
    */
    private String name;
    /**
    * 路由路径
    */
    private String path;
    /**
    * 描述
    */
    @Column(name = "`desc`")
    private String desc;
    /**
    * 父级菜单
    */
    private Long parentId;
    /**
    * 路由路径层级
    */
    private Integer depth;
    /**
    * 路由路径
    */
    private String dept;
    /**
    * 从1开始的序号,排序用
    */
    @Column(name = "`index`")
    private Integer index;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    @Version
    private Long version;


    @Column(name = "is_leaf")
    private Boolean isLeaf;
}