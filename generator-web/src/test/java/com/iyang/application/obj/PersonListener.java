package com.iyang.application.obj;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   洛红 on 2021/5/19 19:22
 */

@Slf4j
public class PersonListener extends AnalysisEventListener<Person> {

    List<Person> personList = new ArrayList<>();

    @Override
    public void invoke(Person person, AnalysisContext analysisContext) {

        log.info("解析到的数据是 ---> {} " , person);
        personList.add(person);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("回调成功,集合的数据是 ---> {} " ,personList);
    }

}
