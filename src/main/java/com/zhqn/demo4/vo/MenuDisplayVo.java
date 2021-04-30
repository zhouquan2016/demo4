package com.zhqn.demo4.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: MenuDisplayVo
 * Date:     2021/4/30 10:22
 * Description:
 * @author zhouquan3
 */
@Data
public class MenuDisplayVo implements Serializable {

    List<MenuVo> header;

    String activeId;
}
