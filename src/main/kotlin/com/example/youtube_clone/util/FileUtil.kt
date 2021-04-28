package com.example.youtube_clone.util

import com.coremedia.iso.IsoFile
import com.googlecode.mp4parser.DataSource
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.audio.mp3.MP3File
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import javax.activation.FileDataSource
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem


class FileUtil {
  companion object {
    fun getDuration(file: File): Float {
      val fileName = file.name
      val suffix = fileName.split(".").last()
      if (suffix.equals("wav")) {
        val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(file)
        val format: AudioFormat = audioInputStream.format
        val audioFileLength = file.length()
        val frameSize = format.frameSize
        val frameRate = format.frameRate
        return audioFileLength / (frameSize * frameRate)
      } else if (suffix.equals("mp3")) {
        val mp3File = AudioFileIO.read(file) as MP3File
        val audioHeader = mp3File.audioHeader
        return audioHeader.trackLength.toFloat()
      } else {
        val isoFile = IsoFile(file.path)
        val lengthInSeconds = isoFile.movieBox.movieHeaderBox.duration /
            isoFile.movieBox.movieHeaderBox.timescale
        return lengthInSeconds.toFloat()
      }
    }

    fun convertMultipartToFile(multipartFile: MultipartFile): File {
//      val file = File(multipartFile.originalFilename)
//      file.createNewFile()
//      val fos = FileOutputStream(file)
//      fos.write(multipartFile.bytes)
//      fos.close()
//      return file
      val fileName = multipartFile.originalFilename!!
      val temp = fileName.split(".")
      val prefix = temp.first()
      val suffix = ".${temp.last()}"
      val file = File.createTempFile(prefix, suffix)
      multipartFile.transferTo(file)
      return file
    }
  }
}
