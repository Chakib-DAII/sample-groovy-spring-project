package com.example.samplegroovyproject.service

import com.example.samplegroovyproject.pojo.Customer
import com.example.samplegroovyproject.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import javax.json.JsonPatch
import javax.json.JsonStructure
import javax.json.JsonValue


/**
 * @author cdaii* @created 22/04/2022 - 3:49 AM
 * @project sample-groovy-project
 * */

@Service
class CustomerService {

    private final CustomerRepository customerRepository
    private final ObjectMapper objectMapper

    CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository
        this.objectMapper = objectMapper
    }


    Flux<Customer> getAllCustomers(){
        return this.customerRepository.findAll()
    }

    Mono<Customer> getCustomerById(int customerId){
        return this.customerRepository.findById(customerId)
    }

    Mono<Customer> createCustomer(final Customer customer){
        return this.customerRepository.save(customer)
    }

    Mono<Customer> updateCustomer(int customerId, final Mono<Customer> customerMono){
        return this.customerRepository.findById(customerId)
                .flatMap(customerDb -> customerMono.map(customer -> {
                    customer.setId(customerDb.getId())
                    return customer
                }))
                .flatMap(customer -> this.customerRepository.save(customer))
    }
    Mono<Customer> patchCustomer(int customerId, final Mono<JsonPatch> customerPatchMono){
        return this.customerRepository.findById(customerId)
                .flatMap(customerDb -> customerPatchMono.map(customer -> {
                    //Converts the original user to a JsonStructure
                    JsonStructure target = objectMapper.convertValue(customerDb, JsonStructure.class)
                    //Applies the patch to the original user
                    JsonValue patchedCustomer = customer.apply(target)
                    //Converts the JsonValue to a User instance and save
                    return this.objectMapper.convertValue(patchedCustomer, Customer.class)
                }))
                .flatMap(customer -> this.customerRepository.save(customer))
    }

    Mono<Void> deleteCustomer(final int id){
        return this.customerRepository.deleteById(id)
    }
}
