package com.pennywiser.conf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.time.Clock

@Configuration
class AppConfiguration {
    @Bean
    @Profile("!test")
    fun clock() = Clock.systemDefaultZone()
}