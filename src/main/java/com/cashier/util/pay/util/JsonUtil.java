package com.cashier.util.pay.util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 *
 * @ClassName: JsonUtil
 * @description
 *
 * @author lujinpeng
 * @createDate 2018年10月31日-下午2:26:00
 */
public class JsonUtil {

	
	public static String getResponseJson(Integer code, String msg, Integer count, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		if (count == null) {
			count = 0;
		}
		if (obj == null) {
			obj = new ArrayList<>();
		}
		map.put("data", obj);
		String json = JSONObject.toJSONString(map);

		return json;
	}

	/**
	 * 获取json
	 * 
	 * @param url
	 * @return
	 */
	public static String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 *      * Map转成实体对象      * @param map map实体对象包含属性      * @param clazz 实体对象类型
	 *      * @return      
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
		if (map == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	

}
