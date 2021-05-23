package com.iyang.application.files;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by   洛红 on 2021/5/21 17:18
 */

@Slf4j
public class FileInfoReadTest {

    @Test
    public void classPathFilesInfoRead() throws Exception {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:templates/code-generator/query/*.ftl");
        Map<String,String> fileNameContent = new HashMap();
        for(Resource resource:resources){
            String filename = resource.getFilename();
            File file = resource.getFile();

            BufferedReader reader = null;
            String allFileContent = "";
            try{
                reader = new BufferedReader(new FileReader(file));

                String fileContent = "";
                int line = 1;
                while ((fileContent = reader.readLine()) != null) {
                    // 显示行号
                    allFileContent += fileContent;
                    allFileContent += "\n";
                    line++;
                }
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }

            log.info("The filename is ---> {} " ,filename);
            log.info("文件内容 ---> {} " , allFileContent);
        }

    }


}
