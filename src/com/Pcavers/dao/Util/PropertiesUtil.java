package com.Pcavers.dao.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 工具类，�?要先调用实例方法,设置资源文件路径，才能获�?/设置文件内容�?
 * getproperties获取properties文件中的内容!
 * getInstance获取文件实例�?
 * */
public class PropertiesUtil {
	private static Properties properties;
	private static PropertiesUtil propertiesUtil;

	private PropertiesUtil(String filePath) {
		if (properties == null) {
			properties = new Properties();
		}
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesUtil getInstance(String filePath) {
		if (propertiesUtil == null) {
			propertiesUtil = new PropertiesUtil(filePath);
		}
		return propertiesUtil;
	}

	public static String getProperties(String key) {
		if (properties != null && key != null) {
			return properties.getProperty(key);
		}else {
			return null;
		}
	}
	
	public static Object setProperties(File filePath,String key,String value) {
		Object object = false;
		if(properties != null && key != null) {
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(filePath);
				object = properties.setProperty(key, value);
				properties.store(out, "写入测试");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(out!=null)
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return object;
	}
}
