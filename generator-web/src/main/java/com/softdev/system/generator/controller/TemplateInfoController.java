package com.softdev.system.generator.controller;

import com.softdev.system.generator.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by   洛红 on 2021/5/21 17:17
 */

@Slf4j
@RestController
@RequestMapping(value = "/query")
public class TemplateInfoController {



    @GetMapping(value = "/allInfo")
    public Object allInfo()  throws Exception{

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:templates/code-generator/query/*.ftl");
        Map<String,String> resourceMap = new HashMap<>();
        
        for(Resource resource :resources){
            File file = resource.getFile();
            String filename = resource.getFilename();
            resourceMap.put(filename, FileUtils.readFileContentByFile(file));
        }

        return resourceMap;
    }


}
