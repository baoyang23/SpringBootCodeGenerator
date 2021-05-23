package com.softdev.system.generator.entity.execl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

/**
 * Created by   洛红 on 2021/5/19 19:56
 * 通过 execl 生成代码接收对象
 */

@ToString
@Data
public class GenerateExeclInfo {

    @ExcelProperty("clazzName")
    private String clazzName;

    @ExcelProperty("tag")
    private String tag;

    @ExcelProperty("funName")
    private String funName;

    @ExcelProperty("notes")
    private String notes;
    @ExcelProperty("requestMethod")
    private String requestMethod;
    @ExcelProperty("paramJson")
    private String paramJson;
    @ExcelProperty("paramNoteJson")
    private String paramNoteJson;
    @ExcelProperty("packageName")
    private String packageName;
    @ExcelProperty("mybatisRespType")
    private String mybatisRespType;
    @ExcelProperty("sql")
    private String sql;
    @ExcelProperty("funcRespType")
    private String funcRespType;
    @ExcelProperty("logicType")
    private String logicType;

//    private JSONObject paramJson;
//    private JSONObject paramNoteJson;

}
