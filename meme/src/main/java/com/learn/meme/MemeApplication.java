package com.learn.meme;

import com.learn.meme.security.jwt.JwtConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemeApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
