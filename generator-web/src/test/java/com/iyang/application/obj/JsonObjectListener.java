package com.iyang.application.obj;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by   洛红 on 2021/5/19 19:44
 */

@Slf4j
public class JsonObjectListener extends AnalysisEventListener<JSONObject> {


    @Override
    public void invoke(JSONObject jsonObject, AnalysisContext analysisContext) {

        log.info("接受到的数据 ---> {} " , jsonObject);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
