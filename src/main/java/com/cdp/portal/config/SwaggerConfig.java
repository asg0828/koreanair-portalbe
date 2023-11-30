package com.cdp.portal.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
      Info info = new Info()
          .title("CDP API")
          .version("1.0")
          .description("CDP API Spec");

      return new OpenAPI()
          .components(new Components())
          .info(info);
    }
    
    /**
     * bo 그룹
     * @return
     */
    @Bean
    public GroupedOpenApi boGroup() {
        final String[] path = {"/bo/**"};
        
        return GroupedOpenApi.builder()
                .group("bo")
                .pathsToMatch(path)
                .build();
    }
    
    /**
     * fo 그룹
     * @return
     */
    @Bean
    public GroupedOpenApi foGroup() {
        final String[] path = {"/fo/**"};
        
        return GroupedOpenApi.builder()
                .group("fo")
                .pathsToMatch(path)
                .build();
    }
    
    /**
     * ext 그룹
     * @return
     */
    @Bean
    public GroupedOpenApi extGroup() {
        final String[] path = {"/ext/**"};
        
        return GroupedOpenApi.builder()
                .group("ext")
                .pathsToMatch(path)
                .build();
    }


}
