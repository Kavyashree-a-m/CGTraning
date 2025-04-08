package com.magicbricks.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
 
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
 
public class JsonUtil {
 
	public static Map<String, Object> readJson(String filePath)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
