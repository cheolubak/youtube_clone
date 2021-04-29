package com.example.youtube_clone.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerConfig {
  private fun apiInfo(): ApiInfo {
    return ApiInfoBuilder()
        .title("VIDEO")
        .description("VIDEO API")
        .version("0.1")
        .build()
  }

  private fun getConsumeContentTypes(): MutableSet<String> {
    val consumes = mutableSetOf<String>()
    consumes.add("application/json;charset=UTF-8")
    consumes.add("application/x-www-form-urlencoded")
    return consumes
  }

  private fun getProduceContentTypes(): MutableSet<String> {
    val produces = mutableSetOf<String>()
    return produces
  }

  @Bean
  fun commonApi(): Docket {
    return Docket(DocumentationType.SWAGGER_2)
        .consumes(getConsumeContentTypes())
        .produces(getProduceContentTypes())
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.youtube_clone.controller"))
        .paths(PathSelectors.ant("/*"))
        .build()
  }
}