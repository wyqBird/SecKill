package com.wyq.secondkill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SecondkillApplication{

	public static void main(String[] args) {
		SpringApplication.run(SecondkillApplication.class, args);
	}

}
/* 打war包用
@SpringBootApplication
public class SecondkillApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SecondkillApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SecondkillApplication.class);
	}
}
*/
