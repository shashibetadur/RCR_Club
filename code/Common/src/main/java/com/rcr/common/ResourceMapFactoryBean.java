package com.rcr.common;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ResourceMapFactoryBean extends PropertiesFactoryBean implements ResourceLoaderAware {

	private String resourceBasePath = "";

	private ResourceLoader resourceLoader = new DefaultResourceLoader();


	public void setResourceBasePath(String resourceBasePath) {
		this.resourceBasePath = (resourceBasePath != null ? resourceBasePath : "");
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
	}

	public Properties createProperties() throws IOException {
		Properties resourceMap = new Properties();
		Properties props = mergeProperties();
		for (Enumeration en = props.propertyNames(); en.hasMoreElements();) {
			String key = (String) en.nextElement();
			String location = props.getProperty(key);
			resourceMap.put(key, getResource(location));
		}
		return resourceMap;
	}

	protected Resource getResource(String location) {
		return this.resourceLoader.getResource(this.resourceBasePath + location);
	}

}
