package com.softdev.system.generator.entity.query;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * Created by   洛红 on 2021/5/19 0:06
 */

@Data
public class QueryInfo {

    // 包
    private String packageName;
    // 类名字
    private String clazzName;
    // 方法
    private String funName;
    // 注释
    private String notes;
    // mybatis 参数
    private String mybatisParam;
    // 参数 , 参数名字对应类型,比如:  name:String
    private JSONObject paramJson;
    // 字段注释json,用于controller,使用方法名字 + 参数 : 注释意思.
    private JSONObject paramNoteJson;
    // 请求方法,用于 controller 曾.  GetMapping/PostMapping/PutMapping/DeleteMapping
    private String requestMethod;
    //  swagger-ui 的 tag 字段.
    private String tag;

    // sql 语句
    private String sql;
    // mybatis 返回的类型
    private String mybatisRespType;

    // 方法返回类型
    private String funcRespType;

}
