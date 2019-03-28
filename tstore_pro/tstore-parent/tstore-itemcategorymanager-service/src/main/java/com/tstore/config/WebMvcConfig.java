package com.tstore.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {
	@Bean
	public FormContentFilter formContentFilter() {
		return new FormContentFilter();
	}

}