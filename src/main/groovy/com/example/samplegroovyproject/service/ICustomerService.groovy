package com.example.samplegroovyproject.service

import com.example.samplegroovyproject.pojo.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import javax.json.JsonPatch


/**
 * @author cdaii* @created 23/04/2022 - 4:47 PM
 * @project sample-groovy-project
 * */

interface ICustomerService {
    Flux<Customer> getAllCustomers()
    Mono<Customer> getCustomerById(int customerId)
    Mono<Customer> createCustomer(final Customer customer)
    Mono<Customer> updateCustomer(int customerId, final Mono<Customer> customerMono)
    Mono<Customer> patchCustomer(int customerId, final Mono<JsonPatch> customerPatchMono)
    Mono<Void> deleteCustomer(final int id)
}