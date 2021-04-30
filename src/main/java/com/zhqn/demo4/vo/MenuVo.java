package com.zhqn.demo4.vo;

import com.zhqn.demo4.entity.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: MenuVo
 * Date:     2021/4/29 17:57
 * Description:
 * @author zhouquan3
 */
@Data
public class MenuVo implements Serializable {

    private String id;

    private String name;

    private String path;

    private Integer childCount;

    private Boolean isLeaf;

    private boolean active;

    private List<MenuVo> children;

    private String parentId;

}
