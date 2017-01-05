package com.cooksys.polar.impl

import com.cooksys.polar.Client
import com.cooksys.polar.ClientService
import com.cooksys.polar.Config
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ClientServiceImpl(val config: Config) : ClientService {

    private val rest = RestTemplate()

    @Cacheable("clientName")
    override fun getClient(id: String): Client? = rest.getForObject(config.grizzlyUrl.toString() + "/client/$id", Client::class.java)



}