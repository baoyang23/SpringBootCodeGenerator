package com.softdev.system.generator.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by   洛红 on 2021/5/21 18:57
 */

@Slf4j
public class FileUtils {

    private FileUtils(){}


    public static String readFileContentByFile(File file){

        BufferedReader reader = null;
        String allFileContent = "";
        try{
            reader = new BufferedReader(new FileReader(file));

            String fileContent = "";
            int line = 1;
            while ((fileContent = reader.readLine()) != null) {
                // 显示行号
                allFileContent += fileContent;
                allFileContent += (line != 1 ?"\n":"");
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
        return allFileContent;
    }


}
