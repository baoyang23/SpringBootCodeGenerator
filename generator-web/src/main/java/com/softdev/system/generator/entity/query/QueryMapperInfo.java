package com.softdev.system.generator.entity.query;

import lombok.Data;

import java.util.List;

/**
 * Created by   洛红 on 2021/5/19 9:35
 * 生成一个 mapper 对应的标签
 */

@Data
public class QueryMapperInfo {

    // 生成的包名字
    private String packageName;

     // 生成的文件名字
    private String clazzName;

    // 生成多个 query 标签的集合
    private List<QueryInfo> queryInfoList;

}
