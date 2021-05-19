package com.iyang.application.obj;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softdev.system.generator.entity.query.*;
import com.softdev.system.generator.entity.execl.GenerateExeclInfo;
import com.softdev.system.generator.util.FreemarkerUtil;
import com.softdev.system.generator.util.ImportTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by   洛红 on 2021/5/19 20:00
 */

@Slf4j
public class GenerateExeclInfoListener extends AnalysisEventListener<GenerateExeclInfo> {

    private Map<String, List<GenerateExeclInfo>> CLAZZ_NAME_MAP = new HashMap<>(256);
    private List<GenerateExeclInfo> CLASS_NAME_LIST = new ArrayList<>();

    @Override
    public void invoke(GenerateExeclInfo generateExeclInfo, AnalysisContext analysisContext) {

        log.info("读取execl 每行的数据 ---> {} "  , generateExeclInfo);
        List<GenerateExeclInfo> infos = CLAZZ_NAME_MAP.getOrDefault(generateExeclInfo.getClazzName(), new ArrayList<>());
        infos.add(generateExeclInfo);
        CLAZZ_NAME_MAP.put(generateExeclInfo.getClazzName(),infos);

        CLASS_NAME_LIST.add(generateExeclInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext)  {

        Map<String, List<GenerateExeclInfo>> listMap = CLASS_NAME_LIST.stream().collect(Collectors.groupingBy(GenerateExeclInfo::getClazzName));

        log.info("the listMap 转化后的值是 ---> {} " , JSON.toJSONString(listMap));
        log.info("the CLAZZ_NAME_MAP 转化后的值是 ---> {} " , JSON.toJSONString(CLAZZ_NAME_MAP));

        // 生成 controller / mapper / mapper.xml 文件.
        // 生成 xml 文件
        Map<String, QueryMapperInfo> xmlMapperInfoMap = new HashMap<>();
        Map<String, QueryMapperClazzInfo> mapperClazzInfoMap = new HashMap<>();
        Map<String, ControllerInfo> controllerInfoMap = new HashMap<>();
        Map<String, QueryServiceImplInfo> queryServiceImplInfoMap = new HashMap<>();

        for(Map.Entry<String,List<GenerateExeclInfo>> entry : CLAZZ_NAME_MAP.entrySet()){
            String clazzName = entry.getKey();
            List<GenerateExeclInfo> value = entry.getValue();
            QueryMapperInfo queryMapperInfo = new QueryMapperInfo();
            List<QueryInfo> queryInfoList = new ArrayList<>(value.size());
            for(GenerateExeclInfo generateExeclInfo : value){

                log.info("generateExeclInfo 遍历数据是 ---> {} " , generateExeclInfo);
                QueryInfo info = new QueryInfo();
                BeanUtils.copyProperties(generateExeclInfo,info);

                info.setParamNoteJson(JSON.parseObject(generateExeclInfo.getParamNoteJson()));
                info.setParamJson(JSON.parseObject(generateExeclInfo.getParamJson()));
                queryInfoList.add(info);

            }

            queryMapperInfo.setQueryInfoList(queryInfoList);
            queryMapperInfo.setClazzName(clazzName);
            queryMapperInfo.setPackageName(value.get(0).getPackageName());

            List<String> typeList = new ArrayList<>();
            typeList.add("java");
            typeList.add("mybatis");
            QueryMapperClazzInfo queryMapperClazzInfo = new QueryMapperClazzInfo();
            queryMapperClazzInfo.setClazzAuthor("luohong");
            queryMapperClazzInfo.setClazzName(clazzName);
            queryMapperClazzInfo.setQueryInfoList(queryInfoList);
            queryMapperClazzInfo.setTypeJson(ImportTypeEnum.convertTypeToJson(typeList));

            ControllerInfo controllerInfo = new ControllerInfo();
            controllerInfo.setTag(value.get(0).getTag());
            controllerInfo.setControllerList(queryInfoList);
            controllerInfo.setClazzName(clazzName);

            List<String> serviceTypeList = new ArrayList<>();
            serviceTypeList.add("java");
            QueryServiceImplInfo queryServiceImplInfo = new QueryServiceImplInfo();
            queryServiceImplInfo.setClazzName(clazzName);
            queryServiceImplInfo.setServiceList(queryInfoList);
            queryServiceImplInfo.setTypeJson(ImportTypeEnum.convertTypeToJson(serviceTypeList));

            xmlMapperInfoMap.put(clazzName,queryMapperInfo);
            mapperClazzInfoMap.put(clazzName,queryMapperClazzInfo);
            controllerInfoMap.put(clazzName,controllerInfo);
            queryServiceImplInfoMap.put(clazzName,queryServiceImplInfo);
        }


        generateMapperXml(xmlMapperInfoMap);
        generateMapperInterface(mapperClazzInfoMap);
        generateServiceImpl(queryServiceImplInfoMap);
        generateController(controllerInfoMap);


    }

    public void generateServiceImpl(Map<String, QueryServiceImplInfo> queryServiceImplInfoMap){

        for(Map.Entry<String,QueryServiceImplInfo> entry : queryServiceImplInfoMap.entrySet()){
            QueryServiceImplInfo value = entry.getValue();
            JSONObject json = JSON.parseObject(JSON.toJSONString(value));
            String content = "";
            try{
                content = FreemarkerUtil.processString("/query/serviceImpl.ftl", json);
            }catch (Exception e){
                log.error("生成模板错误 ---> {} " ,e );
            }

            log.info("serviceImpl.ftl 生成后的内容是 ---> {} " , content);
        }

    }

    // 生成 xml
    private void generateMapperXml(Map<String,QueryMapperInfo> xmlMapperInfoMap){


        for(Map.Entry<String,QueryMapperInfo> entry : xmlMapperInfoMap.entrySet()){
            String key = entry.getKey();
            QueryMapperInfo value = entry.getValue();
            JSONObject json = JSON.parseObject(JSON.toJSONString(value));
            String content = "";
            try{
                content = FreemarkerUtil.processString("/query/mapper.ftl", json);
            }catch (Exception e){
                log.error("生成模板错误 ---> {} " ,e );
            }

            log.info("mapper.xml 生成后的内容是 ---> {} " , content);
        }

    }

    // 生成 mapper 接口类.
    private void generateMapperInterface(Map<String,QueryMapperClazzInfo> mapperClazzInfoMap){


        for(Map.Entry<String,QueryMapperClazzInfo> entry : mapperClazzInfoMap.entrySet()){
            QueryMapperClazzInfo value = entry.getValue();
            JSONObject json = JSON.parseObject(JSON.toJSONString(value));
            String content = "";
            try{
                content = FreemarkerUtil.processString("/query/mapperClazz.ftl", json);
            }catch (Exception e){
                log.error("生成模板错误 ---> {} " ,e );
            }
            log.info("mapperClazz.ftl 生成后的内容是 ---> {} " , content);
        }

    }

    // 生成 controller 层面的代码
    private void generateController(Map<String,ControllerInfo> controllerInfoMap){

        for(Map.Entry<String,ControllerInfo> entry : controllerInfoMap.entrySet()){
            ControllerInfo value = entry.getValue();
            JSONObject json = JSON.parseObject(JSON.toJSONString(value));
            String content = "";
            try{
                content = FreemarkerUtil.processString("/query/controller.ftl", json);
            }catch (Exception e){
                log.error("生成模板错误 ---> {} " ,e );
            }
            log.info("controller.ftl 生成后的内容是 ---> {} " , content);

        }

    }

}
