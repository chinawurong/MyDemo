package com.clduo.test;

import java.util.HashMap;
import java.util.Map;

/** 应用内数据传输
 * @author wanglei 2013-12-26 下午7:43:09 */
public class Data {

	private Map<String, String> dataMap;
	private Map<String, Object> listMap;

	public Data() {
		dataMap = new HashMap<String, String>();
		listMap = new HashMap<String, Object>();
	}

	public Data getData(String key) {
		if (listMap.get(key) == null) {
			Data data = new Data();
			listMap.put(key, data);
			return data;
		}
		return (Data) listMap.get(key);
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public Map<String, Object> getListMap() {
		return listMap;
	}

}