package com.softdev.system.generator.entity.execl;

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

    private String clazzName;
    private String tag;
    private String funName;
    private String notes;
    private String requestMethod;
    private String paramJson;
    private String paramNoteJson;

    private String packageName;
    private String mybatisRespType;
    private String sql;

    private String funcRespType;

//    private JSONObject paramJson;
//    private JSONObject paramNoteJson;

}
