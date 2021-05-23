package com.dtwave.screen.service.impl;

<#if typeJson?exists>
    <#list typeJson?keys as key>
import ${typeJson[key]};
    </#list>
</#if>

/**
* @author: luohong
* @Desc:
*/

@Service
public class ${clazzName}ServiceImpl {

    @Autowired
    private ${clazzName}Mapper ${clazzName}Mapper;

    <#list serviceList as c>
        // ${c.notes}
        public Object ${c.funName}(<#if c.paramJson?exists><#list c.paramJson?keys as key>${c.paramJson[key]} ${key}<#if key_has_next>,</#if></#list></#if>){
            <#if c.logicType?exists && c.logicType == 'sum'>
                // 计算逻辑
                List<Map> dataList = ${clazzName}Mapper.${c.funName}(<#if c.paramJson?exists><#list c.paramJson?keys as key>${c.paramJson[key]} ${key}<#if key_has_next>,</#if></#list></#if>);
                long allCount = dataList.stream().map(map -> Integer.parseInt(map.getOrDefault("count","0").toString()))
                        .collect(Collectors.summarizingInt(s ->s)).getSum();
                retrun mapList.stream().map(map -> {
                    JSONObject json = new JSONObject();
                    json.putAll(map);
                    int num = Integer.parseInt(map.getOrDefault("count", "0").toString());
                    double value = NumberUtil.div(num + "", allCount + "", 2).doubleValue();
                    json.put("percent", value);
                    return json;
                });
            <#elseif c.logicType?exists && c.logicType == 'return'>
                return ${clazzName}Mapper.${c.funName}(<#if c.paramJson?exists><#list c.paramJson?keys as key>${c.paramJson[key]} ${key}<#if key_has_next>,</#if></#list></#if>);
            <#elseif c.logicType?exists && c.logicType == 'dateLogic'>
            <#else >
                return null;
            </#if>
        }

    </#list>


}