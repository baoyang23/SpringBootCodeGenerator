package com.iyang.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softdev.system.generator.Application;
import com.softdev.system.generator.entity.query.ControllerInfo;
import com.softdev.system.generator.entity.query.QueryInfo;
import com.softdev.system.generator.entity.query.QueryMapperClazzInfo;
import com.softdev.system.generator.entity.query.QueryMapperInfo;
import com.softdev.system.generator.util.FreemarkerUtil;
import com.softdev.system.generator.util.ImportTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by   洛红 on 2021/5/19 0:04
 */


@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:application-dev.yml")
public class MapperXmlTest {

    @Autowired
    private ApplicationContext applicationContext;

    private List<String> list = new ArrayList<>();

    @Before
    public void beforeInitData(){
        list.add("java");
        list.add("mybatis");
    }

    @Test
    public void generateControllerClazz() throws Exception {

        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setNotes("请求接口测试");
        queryInfo.setFunName("test");
        queryInfo.setRequestMethod("GetMapping");

        JSONObject paramJson = new JSONObject();
        paramJson.put("day","String");

        JSONObject paramNoteJson = new JSONObject();
        paramNoteJson.put("testday","天");

        queryInfo.setParamJson(paramJson);
        queryInfo.setParamNoteJson(paramNoteJson);

        List<QueryInfo> queryInfoList = new ArrayList<>();
        queryInfoList.add(queryInfo);

        ControllerInfo controllerInfo = new ControllerInfo();
        controllerInfo.setTag("测试tag");
        controllerInfo.setClazzName("Test");
        controllerInfo.setControllerList(queryInfoList);

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(controllerInfo));
        String processString = FreemarkerUtil.processString("/query/controller.ftl", jsonObject);
        log.info("转换后出来的数据 ---> {} " , processString);

    }


    @Test
    public void generateMapperInterfaceClazz() throws Exception {

        String authorName = "luohong";
        String clazzName = "City";
        Map<String, String> convertTypeJson = ImportTypeEnum.convertTypeToJson(list);

        QueryMapperClazzInfo queryMapperClazzInfo = new QueryMapperClazzInfo();

        List<QueryInfo> queryInfoList = new ArrayList<>();
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setFunName("queryById");
        queryInfo.setNotes("根据id来进行查询");
        queryInfo.setFuncRespType("List<Map>");

        JSONObject paramJson = new JSONObject();
        paramJson.put("day","String");
        paramJson.put("count","int");

        queryInfo.setParamJson(paramJson);

        queryInfoList.add(queryInfo);

        queryMapperClazzInfo = new QueryMapperClazzInfo("",clazzName,authorName,convertTypeJson,queryInfoList);


        String processString = FreemarkerUtil.processString("/query/mapperClazz.ftl", JSON.parseObject(JSON.toJSONString(queryMapperClazzInfo)));

        log.info("最后自动生成的类信息 ---> {} " , processString);

    }

    @Test
    public void getAllBeanInfo() throws Exception {

        String applicationName = applicationContext.getApplicationName();
        log.info("获取出 applicationName 的值是 ---> {} " ,applicationName);
        QueryMapperInfo queryMapperInfo = new QueryMapperInfo();
        queryMapperInfo.setClazzName("CityMapper");
        queryMapperInfo.setPackageName("com.iyang.city");

        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setFunName("queryId");
        queryInfo.setMybatisRespType("map");
        queryInfo.setSql("select id from test");
        List<QueryInfo> queryInfoList = new ArrayList<>();
        queryInfoList.add(queryInfo);

        queryMapperInfo.setQueryInfoList(queryInfoList);

        JSONObject object = JSON.parseObject(JSON.toJSONString(queryMapperInfo));
        Map<String,Object> map = new HashMap<>(object);

        JSONObject typeJson = new JSONObject();
        typeJson.put("String","import java.util.String");

        map.put("typeJson",typeJson);

        String processString = FreemarkerUtil.processString("/query/mapper.ftl", map);

        log.info("最后生成的内容 ---> {} " , processString);

    }


}
