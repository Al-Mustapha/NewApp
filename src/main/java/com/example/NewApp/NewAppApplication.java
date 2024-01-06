package com.example.NewApp;

import com.example.NewApp.Reader.Reader;
import com.example.NewApp.Security.UserPermissions;
import com.google.common.collect.Sets;
import org.hibernate.Filter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;

import java.nio.file.DirectoryStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.NewApp.Security.UserPermissions.CREATE_ARTICLE;
import static com.example.NewApp.Security.UserPermissions.READ_ARTICLE;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NewAppApplication implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String desktopPath = "main/resources/static/images";
		registry.addResourceHandler("/images/**")
				.addResourceLocations(desktopPath);
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	public static void main(String[] args) {
		SpringApplication.run(NewAppApplication.class, args);
	}


}
