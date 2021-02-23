package com.pennywiser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PennyWiserApplication

fun main(args: Array<String>) {
	runApplication<PennyWiserApplication>(*args)
}
