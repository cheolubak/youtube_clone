package com.example.youtube_clone.controller

import com.example.youtube_clone.domain.dto.UploadDTO
import com.example.youtube_clone.service.VideoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/videos")
class VideoController(
  private val videoService: VideoService
) {
  @RequestMapping(
    value = [""],
    method = [RequestMethod.POST]
  )
  fun upload(
    @ModelAttribute uploadDTO: UploadDTO,
    @RequestHeader("CLIENT_KEY") clientKey: String,
    @RequestHeader("Authentication") accessToken: String
  ) {
    videoService.upload(clientKey, accessToken, uploadDTO)
    return
  }
}
