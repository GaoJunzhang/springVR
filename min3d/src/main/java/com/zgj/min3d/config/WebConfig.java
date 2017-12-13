package com.zgj.min3d.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@Component
class WebConfig extends WebMvcConfigurerAdapter {

	@Value("${resource.dir}")
	private String resourceDir;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		File resourceFile = new File(resourceDir);
		String resourceName = resourceFile.getName();
		registry.addResourceHandler("/" + resourceName + "/**").addResourceLocations("file:///" + resourceDir + "/");
	}

}