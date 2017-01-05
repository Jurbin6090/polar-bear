package com.cooksys.polar

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*

fun main(args: Array<String>) {
    SpringApplication.run(PolarBearApplication::class.java, *args)
}

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("/deployment")
class PolarBearApplication(val repo: DeploymentRepository, val clientService: ClientService) {

    @GetMapping
    fun listDeployments(): List<Deployment> = repo.findAll()

    @GetMapping("/{id}")
    fun getDeployment(@PathVariable("id") id: String): Deployment? = repo.findOne(id)

    @PostMapping
    fun createDeployment(@RequestBody deployment : Deployment): Deployment = repo.save(deployment)

    @PutMapping
    fun replaceDeployment(@RequestBody deployment : Deployment): Deployment = repo.save(deployment)

    @DeleteMapping("/{id}")
    fun deleteDeployment(@PathVariable id: String): Unit = repo.delete(id)

    @PatchMapping("/{id}")
    fun patchDeployment(@PathVariable id: String, @RequestBody deployment: PatchDeployment): Deployment {
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
        val saved = repo.save(merged)

        if(existing.id != saved.id) {
            repo.delete(existing.id)
        }

        return saved
    }

    @GetMapping("/employee/{id}")
    fun getEmployeeDeployments(@PathVariable id: Int): DeploymentSummery {

        val (current, previous) = repo.findByEmployeeId(id).partition { it.dates.endDate == null }

        fun toSimple(dep: Deployment): SimpleDeployment = SimpleDeployment(
                deploymentId = dep.id,
                clientId = dep.clientId,
                clientName = clientService.getClient(dep.clientId)?.name?:"unknown")


        return DeploymentSummery(current = current.map(::toSimple), previous = previous.map(::toSimple))
    }
}

