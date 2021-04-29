package com.example.youtube_clone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.example")
class YoutubeCloneApplication

fun main(args: Array<String>) {
  runApplication<YoutubeCloneApplication>(*args)
}
