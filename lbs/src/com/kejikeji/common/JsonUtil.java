package com.kejikeji.common;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	public static String getJsonString(String name,String value){
		try {
			JSONObject json=new JSONObject();
			json.put(name, value);
			
			return json.toString();
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
