package com.cooksys.polar

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*

fun main(args: Array<String>) {
    SpringApplication.run(PolarBearApplication::class.java, *args)
}


@SpringBootApplication
@RestController
class PolarBearApplication(val repo: DeploymentRepository) {

    @GetMapping("/deployment")
    fun listDeployments(): List<Deployment> = repo.findAll()

    @GetMapping("/deployment/{id}")
    fun getDeployment(@PathVariable("id") id: String): Deployment? = repo.findOne(id)

    @PostMapping("/deployment")
    fun createDeployment(@RequestBody deployment : Deployment): Deployment = repo.save(deployment)

    @DeleteMapping("/deployment/{id}")
    fun deleteDeployment(@PathVariable id: String): Unit = repo.delete(id)

    @PutMapping("/deployment")
    fun replaceDeployment(@RequestBody deployment: Deployment): Deployment = repo.save(deployment)

    @PatchMapping("/deployment/{id}")
    fun patchDeployment(@PathVariable id: String, @RequestBody deployment: Deployment): Deployment {

        val existing = repo.findOne(id)

        val merged = existing.copy(
                id = deployment.id ?: existing.id,
                employeeId = deployment.employeeId ?: existing.employeeId,
                recruiter = deployment.recruiter ?: existing.recruiter,
                clientId = deployment.clientId ?: existing.clientId,
                dates = deployment.dates ?: existing.dates,
                address = deployment.address ?: existing.address,
                manager = deployment.manager ?: existing.manager,
                billing = deployment.billing ?: existing.billing)

        return repo.save(merged)
    }
}

