package cn.lhs.filesys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("cn.lhs.filesys.mapper")
public class FilesysApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesysApplication.class, args);
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		//String url = "/opt/soft/myFileSystem/temp/";
		String url = "F:/test/temp/";
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(url);
		return factory.createMultipartConfig();
	}

}
