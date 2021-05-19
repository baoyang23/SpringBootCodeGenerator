package com.softdev.system.generator.entity.query;

import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * Created by   洛红 on 2021/5/19 22:48
 */

@Data
public class QueryServiceImplInfo {

    private String clazzName;

    private List<QueryInfo> serviceList;

    // 类型和导入类型地址.
    private Map<String,String> typeJson;
}
