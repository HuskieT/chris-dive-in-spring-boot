package com.chris.springbootredis.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author lyf
 * 
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
		put("msg", "操作成功");
	}
	
	public int getCode() {
		
		Integer code = (Integer) get("code");
		
		return code != null ? code : 500;
	}
	
	public static R error() {
		return error("网络异常,请稍后再试");
	}
	
	public static R error(String msg) {
		return error(msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}
 

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
