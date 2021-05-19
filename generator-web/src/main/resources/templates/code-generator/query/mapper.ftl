<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${clazzName}Mapper">

    <#list queryInfoList as query>
        <!--  ${query.notes} -->
        <select id="${query.funName}" resultType="${query.mybatisRespType}">
            ${query.sql}
        </select>

    </#list>

</mapper>