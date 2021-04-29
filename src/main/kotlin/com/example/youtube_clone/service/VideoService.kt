package com.example.youtube_clone.service

import com.example.youtube_clone.domain.dto.UploadDTO
import com.example.youtube_clone.domain.entity.Video
import com.example.youtube_clone.domain.enum.VideoStatusType
import com.example.youtube_clone.provider.RequestProvider
import com.example.youtube_clone.provider.S3Provider
import com.example.youtube_clone.repository.AccessTokenRepository
import com.example.youtube_clone.repository.ChannelRepository
import com.example.youtube_clone.repository.VideoRepository
import com.example.youtube_clone.util.FileUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class VideoService(
    private val videoRepository: VideoRepository,
    private val channelRepository: ChannelRepository,
    private val accessTokenRepository: AccessTokenRepository,
    private val requestProvider: RequestProvider,
    private val s3Provider: S3Provider
) {
  private val logger: Logger = LoggerFactory.getLogger(VideoService::class.java)

  @Transactional
  fun upload(
      clientKey: String,
      accessToken: String,
      uploadDTO: UploadDTO
  ) {
    try {
      val videoFile = FileUtil.convertMultipartToFile(uploadDTO.getVideo())
      val duration = FileUtil.getDuration(videoFile)

      val thumbnailFile = FileUtil.convertMultipartToFile(uploadDTO.getThumbnail())

      val videoS3 = s3Provider.upload(videoFile)
      val thumbnailS3 = s3Provider.upload(thumbnailFile)
      val ip = requestProvider.getIp()

      val findAccessToken = accessTokenRepository.findByTokenAndClientKeyAndIp(
          accessToken,
          clientKey,
          ip
      )

      if (findAccessToken.isEmpty) {
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
      }

      val user = findAccessToken.get().getUser()

      val channel = channelRepository.findByIdAndUser(uploadDTO.getChannelId(), user)
      if (channel.isEmpty) {
        throw ResponseStatusException(HttpStatus.FORBIDDEN)
      }

      val video = Video(
          uploadDTO.getName(),
          thumbnailUrl = thumbnailS3,
          videoUrl = videoS3,
          description = uploadDTO.getDescription(),
          status = VideoStatusType.READY,
          duration = duration,
          channel = channel.get()
      )
      videoRepository.save(video)
    } catch (e: Exception) {
      logger.error(e.toString())
      throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @Transactional
  fun getList(
      clientKey: String,
      accessToken: String,
      channelId: Int,
      page: Int,
      size: Int
  ) {
  }
}
