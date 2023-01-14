package com.vti.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompomentConfiguration {
//tạo ra để dễ dàng sử dụng maodel mapper
	@Bean
	public ModelMapper initModelMapper() {
		return new ModelMapper();
	}
}
