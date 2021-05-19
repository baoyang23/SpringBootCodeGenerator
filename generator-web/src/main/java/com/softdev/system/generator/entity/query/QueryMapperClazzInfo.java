package com.softdev.system.generator.entity.query;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by   洛红 on 2021/5/19 9:59
 */

@Data
public class QueryMapperClazzInfo {

    // 生成的包名字
    private String packageName;

    // 生成的文件名字
    private String clazzName;

    // 类的作者信息
    private String clazzAuthor;

    // 类型和导入类型地址.
    private Map<String,String> typeJson;

    // 生成多个 query 标签的集合
    private List<QueryInfo> queryInfoList;

    public QueryMapperClazzInfo() {
    }

    public QueryMapperClazzInfo(String packageName, String clazzName, String clazzAuthor,
                                Map<String, String> typeJson, List<QueryInfo> queryInfoList) {
        this.packageName = packageName;
        this.clazzName = clazzName;
        this.clazzAuthor = clazzAuthor;
        this.typeJson = typeJson;
        this.queryInfoList = queryInfoList;
    }
}
