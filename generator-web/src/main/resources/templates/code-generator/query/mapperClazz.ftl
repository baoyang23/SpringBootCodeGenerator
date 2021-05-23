<#if isAutoImport?exists && isAutoImport>
    import org.apache.ibatis.annotations.Param;
    import org.apache.ibatis.annotations.Mapper;
    import org.springframework.stereotype.Repository;
</#if>

<#if typeJson?exists>
    <#list typeJson?keys as key>
import ${typeJson[key]};
    </#list>
</#if>

/**
* @author luohong
* @Desc:
*/
@Mapper
@Repository
public interface ${clazzName}Mapper {

    <#list queryInfoList as query>
        // ${query.notes}
        ${query.funcRespType} ${query.funName}(<#if query.paramJson?exists><#list query.paramJson?keys as key>@Param("${key}") ${query.paramJson[key]} ${key}<#if key_has_next>,</#if></#list></#if>);

    </#list>


}
