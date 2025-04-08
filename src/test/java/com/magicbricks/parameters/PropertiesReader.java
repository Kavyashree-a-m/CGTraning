package com.magicbricks.parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	private static Properties configProperties = new Properties();
	private static Properties urlProperties = new Properties();
	private static Properties pathProperties = new Properties();

	static {
		loadProperties(configProperties, "src/test/resources/config.properties");
		loadProperties(urlProperties, "src/test/resources/PropertyFiles/url.properties");
		loadProperties(pathProperties, "src/test/resources/PropertyFiles/path.properties");
	}

	// Generic method to load properties
	private static void loadProperties(Properties properties, String filePath) {
		try (FileInputStream fileInput = new FileInputStream(filePath)) {
			properties.load(fileInput);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties file: " + filePath);
		}
	}

	// Method to get values from config.properties
	public static String getConfigProperty(String key) {
		return configProperties.getProperty(key);
	}

	// Method to get values from url.properties
	public static String getUrlProperty(String key) {
		return urlProperties.getProperty(key);
	}

	// Method to get values from path.properties
	public static String getPathProperty(String key) {
		return pathProperties.getProperty(key);
	}
}
