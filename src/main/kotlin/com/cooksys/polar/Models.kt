package com.cooksys.polar

import org.springframework.data.mongodb.core.index.Indexed
import java.time.LocalDate

/**
 * Created by ft4 on 2017-01-03.
 */
data class Deployment(var id: String?,
                      @Indexed var employeeId: Int?,
                      @Indexed var clientId: String?,
                      var recruiter: Int?,
                      var dates: DeploymentDates?,
                      var address: Address?,
                      var manager: Person?,
                      var billing: Billing?)


data class DeploymentDates(var startDate: LocalDate, var endDate: LocalDate, var projectedEndDate: LocalDate)


data class Address(var street1: String, var street2: String, var city: String, var state: String, var zip: String, var country: String)


data class Person(var name: String, var email: String, var phone: String)


data class Billing(var rate: Double, var cycle: String, var taxable: Boolean, var mgmtSvcSurcharge: Double)



data class DeploymentSummery(var current: List<SimpleDeployment>, var previous: List<SimpleDeployment>)

data class SimpleDeployment(var deploymentId: String?, var clientId: String?, var clientName: String?)


data class Client(var id: String, var name: String, var address : Address?)


