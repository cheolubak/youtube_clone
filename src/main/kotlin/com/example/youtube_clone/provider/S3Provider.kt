package com.example.youtube_clone.provider

import com.amazonaws.services.s3.AmazonS3
import com.example.youtube_clone.util.FileUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Component
class S3Provider(
  private val s3: AmazonS3
) {
  @Value("\${aws.access-key}")
  private lateinit var accessKey: String

  @Value("\${aws.secret-key}")
  private lateinit var secretKey: String

  @Value("\${aws.s3.Bucket}")
  private lateinit var bucket: String

  @Value("\${aws.s3.region}")
  private lateinit var region: String

  fun upload(file: File): String {
    val fileName: String = file.name
    val keyName: String = fileName.split(".").first() + "/" + fileName
    s3.putObject(bucket, keyName, file)
    return "${bucket}/${keyName}"
  }
}
