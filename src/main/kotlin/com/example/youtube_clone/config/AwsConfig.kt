package com.example.youtube_clone.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig {
  @Bean
  fun assetS3Client(
    @Value("\${aws.access-key}") accessKey: String,
    @Value("\${aws.secret-key}") secretKey: String
  ): AmazonS3 {
    return AmazonS3ClientBuilder
      .standard()
      .withCredentials(
        AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey))
      )
      .withRegion(Regions.AP_NORTHEAST_2)
      .build()
  }
}
