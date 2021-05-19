package com.iyang.application;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.fastjson.JSONObject;
import com.iyang.application.obj.GenerateExeclInfoListener;
import com.iyang.application.obj.JsonObjectListener;
import com.iyang.application.obj.Person;
import com.iyang.application.obj.PersonListener;
import com.softdev.system.generator.entity.execl.GenerateExeclInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by   洛红 on 2021/5/19 19:17
 *
 * EasyExecl 使用
 */

@Slf4j
public class EasyExeclTest {

    @Test
    public void useJsonObjectReadTest(){

        EasyExcel.read("D:\\dtwave\\generateSql.xlsx",
                GenerateExeclInfo.class,new GenerateExeclInfoListener()).sheet().doRead();

    }

    @Test
    public void easyRead(){

       EasyExcel.read("D:\\easyExeclTest.xlsx",
               Person.class,new PersonListener()).sheet().doRead();


    }



}
