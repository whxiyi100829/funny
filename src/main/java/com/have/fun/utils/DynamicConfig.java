package com.have.fun.utils;


import com.google.common.collect.Lists;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: hzwangxx
 * Date: 14-3-18
 * Time: 11:09
 */
public class DynamicConfig {

    private static final String CONFIG_FILE = "application.properties";
    private static       Logger logger      = LoggerFactory.getLogger(DynamicConfig.class);
    private static PropertiesConfiguration properties;

    /**
     * 初始化
     */
    static {
        try {
            properties = new PropertiesConfiguration(CONFIG_FILE);
            properties.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (ConfigurationException e) {
            logger.error("init monitor config error ", e);
        }
    }

    /**
     * 根据key返回对应value值
     * @param key key值
     * @return 返回对应值，默认null
     */
    public static String getStr(final String key) {
        return getStr(key, null);
    }

    /**
     * 根据key返回对应的value值，没有取defaultValue
     * @param key key值
     * @param defaultValue 默认值
     * @return 返回对应值
     */
    public static String getStr(final String key, final String defaultValue) {
        return properties.getString(key, defaultValue);
    }

    /**
     * 获取所有的key
     * @return
     */
    public static List<String> getKeys() {
        return Lists.newArrayList(properties.getKeys());
    }

    /**
     * 根据key返回对应的value值
     * @param key key值
     * @return 返回对应值，默认值为0
     */
    public static int getInt(final String key) {
        return getInt(key, 0);
    }

    /**
     * 根据key返回对应的value值，没有取defaultValue
     * @param key key值
     * @param defaultValue 默认值
     * @return 返回对应值
     */
    public static int getInt(final String key, final int defaultValue) {
        int value;
        try {
            value = properties.getInt(key, defaultValue);
        } catch (ConversionException e) {
            logger.warn(String.format("invalid value for key %s", key), e);
            value = defaultValue;
        }
        return value;
    }

    /**
     * 根据key返回对应的value值
     * @param key key值
     * @return 返回对应值，默认值为0
     */
    public static long getLong(final String key) {
        return getLong(key, 0L);
    }

    /**
     * 根据key返回对应的value值，没有取defaultValue
     * @param key key值
     * @param defaultValue 默认值
     * @return 返回对应值
     */
    public static long getLong(final String key, final long defaultValue) {
        long value;
        try {
            value = properties.getLong(key, defaultValue);
        } catch (ConversionException e) {
            logger.warn(String.format("invalid value for key %s", key), e);
            value = defaultValue;
        }
        return value;
    }

    /**
     * 根据key返回对应的value值
     * @param key key值
     * @return 返回对应值，默认值为true
     */
    public static boolean getBoolean(final String key) {
        return getBoolean(key, true);
    }

    /**
     * 根据key返回对应的value值，没有取defaultValue
     * @param key key值
     * @param defaultValue 默认值
     * @return 返回对应值
     */
    public static boolean getBoolean(final String key, final boolean defaultValue) {
        boolean value;
        try {
            value = properties.getBoolean(key, defaultValue);
        } catch (ConversionException e) {
            logger.warn(String.format("invalid value for key %s", key), e);
            value = defaultValue;
        }
        return value;
    }

    /**
     * 设置并保存key/value对
     * @param key 对应的key
     * @param value 对应的value
     */
    public synchronized static void setAndSaveProperty(final String key, final Object value) {
        properties.setProperty(key, value);
        try {
            properties.save();
        } catch (ConfigurationException e) {
            logger.warn(String.format("save failure for %s=%s(key/value)", key, value), e);
        }
    }

    /**
     * 设置key/value对，不保存到文件中
     * @param key 对应的key
     * @param value 对应的value
     */
    public static void setProperty(final String key, Object value) {
        properties.setProperty(key, value);
    }
}
