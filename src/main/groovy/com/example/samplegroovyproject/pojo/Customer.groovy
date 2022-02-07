package com.example.samplegroovyproject.pojo


/**
 * @author cdaii* @created 07/02/2022 - 3:06 PM
 * @project sample-groovy-project
 * */

class Customer {
    private Integer id
    private String name

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
