package com.unidadesApi.sic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

    @Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			var authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication instanceof JwtAuthenticationToken jwtAuth) {
				String token = jwtAuth.getToken().getTokenValue();
				requestTemplate.header("Authorization", "Bearer " + token);
			}
		};
	}
}