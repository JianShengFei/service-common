package com.ash.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 李军
 * @version 1.0
 * @datetime 2010-8-11 下午03:23:18 类说明
 */
public class JackJson {
	/**
	 * 对象转json串
	 * 
	 * @param obj
	 *            任意对象对型
	 * @return
	 */
	public static String getBaseJsonData(Object obj) {
		StringWriter writer = new StringWriter();
		if (obj != null) {
			ObjectMapper mapper = new ObjectMapper();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(sdf);
			try {
				mapper.writeValue(writer, obj);
			} catch (Exception e) {
				throw new RuntimeException("对象转json串时出错!", e);
			}
		}
		return writer.toString();
	}

	/**
	 * json数组文本串转集合
	 * 
	 * @param json
	 * @return
	 */
	public static List getListByJsonArray(String json) {
		List<LinkedHashMap<String, Object>> list = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			list = mapper.readValue(json, List.class);
		} catch (Exception e) {
			throw new RuntimeException("json数组文本串转集合时出错!", e);
		}
		return list;
	}

	/**
	 * json串转指定对象
	 * 
	 * @param json
	 * @param c
	 * @return
	 */
	public static Object getObjectByJson(String json, Class c) {
		Object obj = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.readValue(json, c);
		} catch (Exception e) {
			throw new RuntimeException("json串转指定对象时出错!", e);
		}
		return obj;
	}

	/**
	 * 将JSON字符串转换为Map实现类对象
	 * 
	 * @param jsonStr
	 *            json串
	 * @return
	 */
	public static Map getMapByJsonString(String jsonStr) {
		HashMap m = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			m = mapper.readValue(jsonStr, HashMap.class);
		} catch (Exception e) {
			throw new RuntimeException("将JSON字符串转换为Map实现类对象时出错", e);
		}
		return m;
	}

	/**
	 * 将JSON字符串转换为指定Map实现类对象 zhanweibin 2013-4-25
	 * 
	 * @param jsonStr
	 *            json串
	 * @param c
	 *            map对象类型
	 * @return
	 */
	public static Map getMapByJsonString(String jsonStr, Class<? extends Map> c) {
		Map m = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			m = mapper.readValue(jsonStr, c);
		} catch (Exception e) {
			throw new RuntimeException("将JSON字符串转换为指定Map实现类对象时出错!", e);
		}
		return m;
	}

}
