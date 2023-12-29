package com.cdp.portal.external.api.config;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;

import com.cdp.portal.external.api.dto.response.CdpApiResDto;
import com.fasterxml.jackson.databind.type.TypeFactory;

import feign.FeignException;
import feign.Logger;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.AllArgsConstructor;

@Configuration
public class CdpApiConfig {
    
    @AllArgsConstructor
    public static class CdpApiClientDecoder implements Decoder {
        private final Decoder decoder;

        @Override
        public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
            var returnType = TypeFactory.rawClass(type);
            var forClassWithGenerics = ResolvableType.forClassWithGenerics(CdpApiResDto.class, returnType);

            try {
                return ((CdpApiResDto<?>) decoder.decode(response, forClassWithGenerics.getType())).getData();
            } catch (Exception e) {
                return decoder.decode(response, forClassWithGenerics.getType());
            }
        }
    }
    
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
