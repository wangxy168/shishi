package org.rising.dashboard.generate.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;

public class CityUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static String DATA ;
    static {
        try {
            DATA = IOUtils.toString(CityUtils.class.getClassLoader().getResourceAsStream("city.json"),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String randomProvinceCity() {
        try {
            ArrayNode arrayNode = (ArrayNode) MAPPER.readTree(DATA);
            JsonNode province = arrayNode.get(RandomUtils.nextInt(0, arrayNode.size()));
            ArrayNode citys = (ArrayNode)province.get("city");
            JsonNode city = citys.get(RandomUtils.nextInt(0,citys.size()));
            return province.get("name").asText() + "|" + city.get("name").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}