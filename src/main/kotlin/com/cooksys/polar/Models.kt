package com.cooksys.polar

import org.springframework.data.mongodb.core.index.Indexed
import java.time.LocalDate

/**
 * Created by ft4 on 2017-01-03.
 */
data class Deployment(val id: String?,
                      @Indexed val employeeId: Int,
                      @Indexed val projectId: String,
                      val dates: DeploymentDates,
                      val address: Address,
                      val manager: Person?,
                      val billing: Billing?)


data class PatchDeployment(val id: String?,
                           val employeeId: Int?,
                           val projectId: String?,
                           val dates: DeploymentDates?,
                           val address: Address?,
                           val manager: Person?,
                           val billing: Billing?)


data class DeploymentDates(val startDate: LocalDate, val endDate: LocalDate?, val projectedEndDate: LocalDate)


data class Address(val street1: String, val street2: String?, val city: String, val state: String, val zip: String, val country: String)


data class Person(val name: String, val email: String?, val phone: String?)


data class Billing(val rate: Double, val cycle: String, val taxable: Boolean, val mgmtSvcSurcharge: Double?)



data class DeploymentSummery(val current: List<SimpleDeployment>, val previous: List<SimpleDeployment>)

data class SimpleDeployment(val deploymentId: String?, val clientId: String?, val clientName: String?)


data class Client(val id: String, val name: String, val address : Address?)

data class Project(val id: String, val clientId: String)

