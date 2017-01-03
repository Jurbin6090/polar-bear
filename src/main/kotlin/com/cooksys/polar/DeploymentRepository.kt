package com.cooksys.polar

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by ft4 on 2017-01-03.
 */
interface DeploymentRepository : MongoRepository<Deployment, String>
