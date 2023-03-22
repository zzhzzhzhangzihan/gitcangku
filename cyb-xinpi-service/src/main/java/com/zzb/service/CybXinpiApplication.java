package com.zzb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import springfox.boot.starter.autoconfigure.OpenApiAutoConfiguration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
//@MapperScan("com.zzw.xinpi.mapper")//扫描mapper文件夹
/*@EnableWebMvc*/


@Slf4j
@EnableSwagger2
@SpringBootApplication
public class CybXinpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CybXinpiApplication.class, args);
	log.info("启动成功");
	log.info("http://localhost:8080/swagger-ui.html#/");


	}



}
