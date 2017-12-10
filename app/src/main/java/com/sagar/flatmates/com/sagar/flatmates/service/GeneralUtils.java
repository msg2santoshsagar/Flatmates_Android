package com.sagar.flatmates.com.sagar.flatmates.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by santosh sagar on 10-12-2017.
 */

public class GeneralUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String,String> convertValue(Object obj){
        return objectMapper.convertValue(obj, new TypeReference<Map<String,String>>() {});
    }

}
