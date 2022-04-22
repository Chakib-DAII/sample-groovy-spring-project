package com.example.samplegroovyproject.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

/**
 * @author cdaii* @created 07/02/2022 - 3:06 PM
 * @project sample-groovy-project
 * */
class Customer {
    @Id
    @Column("id")
    private Integer id
    @Column("firstName")
    private String firstName
    @Column("lastName")
    private String lastName
    @Column("email")
    private String email

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }
}
