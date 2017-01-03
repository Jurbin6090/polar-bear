package com.cooksys.polar

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.net.URI

/**
 * Created by ft4 on 2017-01-03.
 */
@Component
@ConfigurationProperties("polarBear")
data class Config(var grizzlyUrl: URI = URI(""))

