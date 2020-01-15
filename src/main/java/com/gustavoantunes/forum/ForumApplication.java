package com.gustavoantunes.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
//Esta anotação permite que Pageable seja utilizado sendo passado como 
//parâmentro de um método
@EnableSpringDataWebSupport
//Para hábilitar o serviço de Cache
@EnableCaching
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
