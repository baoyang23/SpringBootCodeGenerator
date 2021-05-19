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

    <#list serviceList as c>
        // ${c.notes}
        public Object ${c.funName}(<#if query.paramJson?exists><#list query.paramJson?keys as key>
        ${query.paramJson[key]} ${key}<#if key_has_next>,</#if></#list></#if>){

            return null;
        }

    </#list>


}