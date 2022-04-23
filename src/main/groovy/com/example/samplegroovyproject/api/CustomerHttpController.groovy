package com.example.samplegroovyproject.api

import com.example.samplegroovyproject.pojo.Customer
import com.example.samplegroovyproject.service.ICustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import javax.json.JsonPatch


/**
 * @author cdaii* @created 07/02/2022 - 3:09 PM
 * @project sample-groovy-project
 * */
@RestController
class CustomerHttpController {
    private final ICustomerService customerService
    private static final PATH = "/customers"

    CustomerHttpController(ICustomerService customerService) {
        this.customerService = customerService
    }

    @GetMapping("/customers")
    Mono<ResponseEntity<Flux<Customer>>> get(){
        return Mono.just(this.customerService.getAllCustomers())
                .map(ResponseEntity::ok)
    }

    @GetMapping("/customers/{id}")
    Mono<ResponseEntity<Customer>> get(@PathVariable("id") Integer id){
        return this.customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    Mono<ResponseEntity<Customer>> post(@RequestBody Customer customer){
        return this.customerService.createCustomer(customer).
                map(c -> ResponseEntity.created(URI.create(PATH)).body(c))
    }

    @PutMapping("/customers")
    Mono<ResponseEntity<Customer>> put(@RequestBody Customer customer, @RequestParam Integer id){
        return this.customerService.updateCustomer(id, Mono.just(customer))
                .map(ResponseEntity::ok)
    }

    /**
     *
     * @param customerPatch
     * @param id
     * @return ResponseEntity<Mono<Customer>>
     *
     * Json Patch operations
     * Each operation consists on the following members:
     *
     * op This is a mandatory parameter, it defines the operation we want to perform. Its value MUST be one of “add”, “remove”, “replace”, “move”, “copy”, or “test”
     * path This is also a mandatory parameter. It defines the target of the operation. From now on we will refer to this field as the “target location”
     * value This parameter can be optional depending on the operation. It contains the value of the operation.
     * add
     * [
     *      { "op": "add", "path": "/alias", "value": "Batman" },
     * ]
     * Adds a new member to the JSON document. In this case it will create the alias property on the document.
     *
     * remove
     * [
     *      { "op": "remove", "path": "/phoneNumber" },
     * ]
     * Removes the value at the the target location.
     *
     * replace
     * [
     *      { "op": "replace", "path": "/address", "value": "batcave" },
     * ]
     * Updates the element at the target location to have the new value.
     *
     * move
     * [
     *    { "op": "move", "from": "/mobilePhoneNumber", "path": "/landlieNumber" }
     * ]
     * This operation is the equivalent of a remove followed by an add. In this case it will move the value of the mobilePhoneNumber to the landlinePhoneNumber field.
     *
     * copy
     * [
     *    { "op": "copy", "from": "/mobilePhoneNumber", "path": "/landlieNumber" }
     * ]
     * This operation copies the value of the “from” field to the target field.
     */
    @PatchMapping(value = "/customers"/*, consumes = "application/json-patch+json"*/)
    Mono<ResponseEntity<Customer>> patch(@RequestBody JsonPatch customerPatch, @RequestParam Integer id){
        return this.customerService.patchCustomer(id, Mono.just(customerPatch))
                .map(ResponseEntity::ok)
    }

    @DeleteMapping("/customers")
    Mono<ResponseEntity<Customer>> delete(@RequestParam Integer id){
        return customerService.deleteCustomer(id)
        .map(ResponseEntity::ok)
    }

}
