package com.ggg.studyApp.config

import com.ggg.studyApp.interceptor.JWTInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig(private val jwtInterceptor: JWTInterceptor) : WebMvcConfigurer{
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8081/")
            .allowedOrigins("http://192.168.35.210:8081")
            .allowedMethods("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/home/api/login",
                "/home/api/sendCertification",
                "/home/api/existsNickname",
                "/home/api/existsId",
                "/home/api/signUp",
            )
    }
}
