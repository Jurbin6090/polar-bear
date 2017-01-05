package com.cooksys.polar.impl

import com.cooksys.polar.Client
import com.cooksys.polar.ClientService
import com.cooksys.polar.Config
import com.cooksys.polar.Project
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ClientServiceImpl(val config: Config) : ClientService {

    private val rest = RestTemplate()

    override fun getClientFromProject(projectId: String): Client? {
        val project = getProject(projectId)

        if(project == null) {
            return null
        }
        else {
            return getClient(project.id)
        }
    }

    @Cacheable("client")
    override fun getClient(clientId: String): Client? = rest.getForObject(config.grizzlyUrl.toString() + "/client/$clientId", Client::class.java)

    @Cacheable("project")
    override fun getProject(projectId : String): Project? = rest.getForObject(config.grizzlyUrl.toString() + "/project/$projectId", Project::class.java)

}