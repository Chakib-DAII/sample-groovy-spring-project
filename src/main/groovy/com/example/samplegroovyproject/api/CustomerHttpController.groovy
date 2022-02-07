package com.example.samplegroovyproject.api

import com.example.samplegroovyproject.pojo.Customer
import com.example.samplegroovyproject.repository.CustomerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


/**
 * @author cdaii* @created 07/02/2022 - 3:09 PM
 * @project sample-groovy-project
 * */
@RestController
class CustomerHttpController {
    private final CustomerRepository customerRepository;

    CustomerHttpController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    Flux<Customer> get(){
        return this.customerRepository.findAll();
    }
}
