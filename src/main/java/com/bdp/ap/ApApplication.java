package com.bdp.ap;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

// @EnableScheduling
@SpringBootApplication
@EnableEncryptableProperties
@EnableAspectJAutoProxy
public class ApApplication {

	@PostConstruct
	public void started() {
		//APP TimeZone KST
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC+09"));
	}

    public static void main(String[] args) {
        SpringApplication.run(ApApplication.class, args);
    }

}
