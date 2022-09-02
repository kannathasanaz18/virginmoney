package com.example.virginmoney.service;

import java.util.List;

public class ResponseWrapper {

    private final List<People> People;


    public ResponseWrapper(List<People> People) {
        this.People = People;
    }


    public List<People> getPeople() {
        return People;
    }
}
