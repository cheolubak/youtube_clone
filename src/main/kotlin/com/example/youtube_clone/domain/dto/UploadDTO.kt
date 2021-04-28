package com.example.youtube_clone.domain.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UploadDTO(
  @NotEmpty
  private val name: String,

  @NotNull
  private val video: MultipartFile,

  @NotNull
  private val thumbnail: MultipartFile,

  @NotEmpty
  private val description: String,

  @NotEmpty
  private val channelId: Int
) {
  fun getName(): String {
    return name
  }

  fun getDescription(): String {
    return description
  }

  fun getThumbnail(): MultipartFile {
    return thumbnail
  }

  fun getVideo(): MultipartFile {
    return video
  }

  fun getChannelId(): Int {
    return channelId
  }
}
