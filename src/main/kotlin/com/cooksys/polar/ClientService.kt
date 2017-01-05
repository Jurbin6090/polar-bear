package com.cooksys.polar

/**
 * Created by ft4 on 2017-01-03.
 */
interface ClientService {

    fun getClientFromProject(projectId: String): Client?

    fun getClient(clientId: String): Client?

    fun getProject(projectId : String): Project?

}