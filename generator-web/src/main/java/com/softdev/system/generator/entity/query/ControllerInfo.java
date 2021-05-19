package com.softdev.system.generator.entity.query;

import lombok.Data;

import java.util.List;

/**
 * Created by   洛红 on 2021/5/19 17:44
 * controller层的自动生成
 * 需要根据 tag + clazzName 来组成唯一的 key, 下面拼接多个方法.
 */

@Data
public class ControllerInfo {

    // 请求tag的类分类.
    private String tag;

    // 即是类的名字,也是请求的路径
    private String clazzName;

    private List<QueryInfo> controllerList;


}
