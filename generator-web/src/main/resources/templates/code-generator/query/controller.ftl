

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author : luohong
* @desc :   旅游事态大屏
* @since : 2021/5/7 / 下午3:28
*/

@Api(value = "${tag}",tags = "${tag}")
@RestController
@RequestMapping(value = "/${clazzName}")
public class ${clazzName}Controller {

    <#list controllerList as c>
        // ${c.notes}
        @ApiOperation(value = "${c.notes}")
        @${c.requestMethod}(value="/${c.funName}")
        public Object ${c.funName}(<#if query.paramJson?exists><#list query.paramJson?keys as key>
                                    @ApiParam("${query.paramNoteJson[c+key]}") ${query.paramJson[key]} ${key}<#if key_has_next>,</#if></#list></#if>){

            return null;
        }
    </#list>

}
