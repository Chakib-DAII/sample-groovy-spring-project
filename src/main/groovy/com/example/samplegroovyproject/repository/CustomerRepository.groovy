package com.example.samplegroovyproject.repository

import com.example.samplegroovyproject.pojo.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository


/**
 * @author cdaii* @created 07/02/2022 - 3:08 PM
 * @project sample-groovy-project
 * */

interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
