package com.kyx.factory.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kyx.factory.support.helper.DateConvertHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * A json util based on jackson.
 *
 * Created by wangle on 3/21/15
 */
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();
    static {
        SimpleModule customModule = new SimpleModule("JsonUtilsModule", new Version(1, 0, 0, "", "", ""));
        customModule.addSerializer(Long.class, new ToStringSerializer());
        customModule.addSerializer(Date.class, new DateConvertHelper.Serializer());
        customModule.addDeserializer(Date.class, new DateConvertHelper.Deserializer());
        mapper.registerModule(customModule)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error("toJson error", e);
            return null;
        }
    }

    /**
     * 将一个 object 转成键值字典.
     */
    public static Map<String, String> toMap(Object value) {
        return mapper.convertValue(value, new TypeReference<Map<String, String>>() {});
    }

    /**
     * 将一个 object 转成键值数组, 一对键值放在相邻的位置.
     * [name1, value1, name2, value2, ..., nameN, valueN]
     */
    public static List<String> toNamesAndValues(Object value) {
        Map<String, String> map = toMap(value);
        List<String> namesAndValues = new ArrayList<>();
        map.entrySet().stream().forEach(entry -> {
            namesAndValues.add(entry.getKey());
            namesAndValues.add(entry.getValue());
        });

        return namesAndValues;
    }

    /**
     * 处理任意 JSON 结构.
     * {T}
     */
    public static <T> T fromPlainJson(String json, Class<T> clazz) {
        try {
            T entity = mapper.readValue(json, clazz);
            return entity;
        } catch (IOException e) {
            LOGGER.error("json to object error", e);
            return null;
        }
    }

    /**
     * Create a new ObjectMapper.
     * @return a new ObjectMapper object
     */
    public static ObjectMapper build() {
        return mapper.copy();
    }
}
