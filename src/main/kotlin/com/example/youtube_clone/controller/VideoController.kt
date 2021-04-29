package com.example.youtube_clone.controller

import com.example.youtube_clone.domain.dto.UploadDTO
import com.example.youtube_clone.service.VideoService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/videos")
class VideoController(
    private val videoService: VideoService
) {
  @ApiOperation(value = "비디오 등록")
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

  @ApiOperation(value = "비디오 리스트")
  @RequestMapping(
      value = ["{channelId}"],
      method = [RequestMethod.GET]
  )
  fun getList(
      @PathParam("channelId") channelId: Int,
      @RequestHeader("CLIENT_KEY") clientKey: String,
      @RequestHeader("Authentication") accessToken: String,
      @RequestParam("page") page: Int = 0,
      @RequestParam("size") size: Int = 20
  ) {

  }
}
