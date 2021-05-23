package com.iyang.application.streams;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by   洛红 on 2021/5/21 14:23
 */


@Slf4j
public class GenereateLogicTest {

    @Test
    public void generateSumLogic() {

        List<Map> dataList = new ArrayList<>();
        long allCount = dataList.stream().map(map -> Integer.parseInt(map.getOrDefault("count", "0").toString()))
                .collect(Collectors.summarizingInt(s -> s)).getSum();
        dataList.stream().map(map -> {
            JSONObject json = new JSONObject();
            json.putAll(map);

            int num = Integer.parseInt(map.getOrDefault("count", "0").toString());
            double value = NumberUtil.div(num + "", allCount + "", 2).doubleValue();
            json.put("percent", value);
            return json;

        }).collect(Collectors.toList());


    }
}
