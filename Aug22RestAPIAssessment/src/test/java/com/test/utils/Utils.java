package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.test.constants.SourcePath;
	
public class Utils {

	public static Properties property = new Properties();
	static {
		property = new Properties();
		String PropertyFilePath = SourcePath.CONFIG_PROPERTIES_PATH;

		try {
			FileInputStream stream = new FileInputStream(PropertyFilePath);
			property.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	public static String getConfigProperty(String key) {
		System.out.println("Key :" + key + " Value: "+ property.getProperty(key));
		return property.getProperty(key);
	}
}
