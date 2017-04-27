package com.panku.pkBaseLibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;


public class SharedPreferencesUtil {

    protected static final String PREFERENCENAME = "app";

    /**
     * 存储本地数据
     *
     * @param c    上下文
     * @param key  要设置的键
     * @param data 要设置的值
     * @throws Exception
     */
    public static void setPreferenceData(Context c, String key, String data)
            throws Exception {
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putString(key, data);
            editor.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 从本地取数据
     *
     * @param c        上下文
     * @param key      要取的键
     * @param defValue 默认值
     * @return
     */
    public static String getPreferenceData(Context c, String key,
                                           String defValue) {
        String t = null;
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            String data = sp.getString(key, defValue);
            t = data;
        } catch (Exception ex) {
            t = null;
        }
        return t;
    }



    /**
     * 存储本地数据 boolean
     *
     * @param c
     * @param key
     * @param value
     * @throws Exception
     */
    public static void setPreferenceData(Context c, String key, boolean value)
            throws Exception {
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 获取本地数据 boolean
     *
     * @param c
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getPreferenceData(Context c, String key,
                                            boolean defValue) {
        boolean t = defValue;
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            t = sp.getBoolean(key, defValue);
        } catch (Exception ex) {
            t = defValue;
        }
        return t;
    }

    /**
     * 从本地取数据,并生成对应实体类
     *
     * @param c        上下文
     * @param key      要取的键
     * @param classOfT 返回的实体类的类型
     * @return 返回实体类，或 null
     */
    public static <T extends Object> T getPreferenceModel(Context c,
                                                          String key, Class<T> classOfT) {
        T t = null;
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            String data = sp.getString(key, "");
            if (data.length() > 0) {
                t = JSON.parseObject(data, classOfT);
            }
        } catch (Exception ex) {
            t = null;
        }
        return t;
    }

    /**
     * 存储本地数据
     *
     * @param c    上下文
     * @param key  要设置的键
     * @param data 要设置的值
     * @throws Exception
     */
    public static void setPreferenceIntData(Context c, String key, int data)
            throws Exception {
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putInt(key, data);
            editor.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 存储本地数据
     *
     * @param c    上下文
     * @param key  要设置的键
     * @param data 要设置的值
     * @throws Exception
     */
    public static void setPreferenceLongData(Context c, String key, long data)
            throws Exception {
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putLong(key, data);
            editor.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 从本地取数据
     *
     * @param c
     * @param key
     * @param defValue
     * @return
     */
    public static long getPreferenceLongData(Context c, String key,
                                             long defValue) {
        long t = 0;
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            t = sp.getLong(key, defValue);
        } catch (Exception ex) {
            t = 0;
        }
        return t;
    }

    /**
     * 从本地取数据
     *
     * @param c        上下文
     * @param key      要取的键
     * @param defValue 默认值
     * @return
     */
    public static int getPreferenceIntData(Context c, String key, int defValue) {
        int t = 0;
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            int data = sp.getInt(key, defValue);
            t = data;
        } catch (Exception ex) {
            t = 0;
        }
        return t;
    }

    /**
     * 删除保存的信息
     *
     * @param c
     * @param key
     */
    public static void deleteSaveInfo(Context c, String key) {
        SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.remove(key);
        edit.commit();
    }

    /**
     * 保存登录用户信息
     *
     * @param c    调用的页面
     * @param data 存储的数据
     * @throws Exception
     */
    public static void setMobileLoginInfo(Context c, String data)
            throws Exception {
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putString("mobileUser", data);
            editor.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 获取登录用户信息
     *
     * @param c        调用的页面
     * @param classOfT 要返回的类
     * @return 如果存取数据正确，则返回登录类实例，否则返回null
     * @throws Exception
     */
    public static <T extends Object> T getMobileLoginInfo(Context c,
                                                          Class<T> classOfT) {
        T t = null;
        try {
            SharedPreferences sp = c.getSharedPreferences(PREFERENCENAME,
                    Context.MODE_PRIVATE);
            String data = sp.getString("mobileUser", "");
            if (data.length() > 0) {
                t = JSON.parseObject(data, classOfT);
            }
        } catch (Exception ex) {
            t = null;
        }
        return t;
    }

}
