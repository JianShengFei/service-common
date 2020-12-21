package com.ash.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author jianshengfei
 * @Description
 * @create 2020-11-24 10:07
 */
public class PropertiesUtil {
	private final static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

	public static Properties getProperties(String config_file_path) throws IOException {
		InputStream in;
		Properties props = new Properties();
		try {
			in = new FileInputStream(config_file_path);
			props.load(in);
		} catch (Exception e) {
			LOG.error("配置文件加载失败：" + e.getMessage(), e);
		}
		return props;
	}

	public static Properties getPropertiesFromClassPath(String filePath) {
		InputStream in;
		Properties props = new Properties();
		try {
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			props.load(in);
		} catch (Exception e) {
			LOG.error("配置文件加载失败：" + e.getMessage(), e);
		}
		return props;
	}

	/**
	 * 设置读编码，解决中文乱码问题
	 * 
	 * @param configFilePath
	 * @param charSet
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties(String configFilePath, String charSet) throws IOException {
		Properties props = new Properties();
		try {
			InputStreamReader ir = null;
			if (charSet == null || charSet.equals("null") || charSet == "null") {
				ir = new InputStreamReader(new FileInputStream(configFilePath));
			} else {
				ir = new InputStreamReader(new FileInputStream(configFilePath), charSet);
			}
			props.load(ir);
		} catch (Exception e) {
			LOG.error("配置文件加载失败：" + e.getMessage(), e);
		}
		return props;
	}

}