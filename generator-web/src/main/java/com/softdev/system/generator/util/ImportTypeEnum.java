package com.softdev.system.generator.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by   洛红 on 2021/5/19 11:18
 * 导入类型mapping    import org.apache.ibatis.annotations.Param;
 *     import org.apache.ibatis.annotations.Mapper;
 *     import org.springframework.stereotype.Repository;
 */

@Slf4j
public enum  ImportTypeEnum {

    String("String","java.lang.String","java"),
    Map("map","java.util.Map","java"),
    List("list","java.util.List","java"),
    PARAM("param","org.apache.ibatis.annotations.Param","mybatis"),
    MAPPER("mapper","org.apache.ibatis.annotations.Mapper","mybatis"),
    REPOSITORY("repository","org.springframework.stereotype.Repository","mybatis");

    private String typeName;
    private String importTypeUrl;
    // 表示这个flag的来源
    private String flag;


    ImportTypeEnum() {
    }

    ImportTypeEnum(java.lang.String typeName, java.lang.String importTypeUrl) {
        this.typeName = typeName;
        this.importTypeUrl = importTypeUrl;
    }

    ImportTypeEnum(java.lang.String typeName, java.lang.String importTypeUrl, java.lang.String flag) {
        this.typeName = typeName;
        this.importTypeUrl = importTypeUrl;
        this.flag = flag;
    }

    public java.lang.String getTypeName() {
        return typeName;
    }

    public void setTypeName(java.lang.String typeName) {
        this.typeName = typeName;
    }

    public java.lang.String getImportTypeUrl() {
        return importTypeUrl;
    }

    public void setImportTypeUrl(java.lang.String importTypeUrl) {
        this.importTypeUrl = importTypeUrl;
    }

    public java.lang.String getFlag() {
        return flag;
    }

    public void setFlag(java.lang.String flag) {
        this.flag = flag;
    }

    /**
     * 根据 flag 表示判断出只需要的数据
     * @param flagList
     * @return
     */
    public static Map<String,String> convertTypeToJson(java.util.List<String> flagList){

        ImportTypeEnum[] values = ImportTypeEnum.values();
        Map<String, String> typeAndUrlMap = Arrays.stream(values).filter(v -> flagList.contains(v.getFlag()))
                .collect(Collectors.toMap(ImportTypeEnum::getTypeName, ImportTypeEnum::getImportTypeUrl));

        return typeAndUrlMap;
    }

}
