package com.bdp.ap;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    // 스프링 부트를 내장 톰캣을 사용하지 않고 외부 톰캣을 사용하기 위한 설정
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApApplication.class);
    }

}
