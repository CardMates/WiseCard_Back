package com.wisecard.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WisecardApiApplication

fun main(args: Array<String>) {
	runApplication<WisecardApiApplication>(*args)
}
