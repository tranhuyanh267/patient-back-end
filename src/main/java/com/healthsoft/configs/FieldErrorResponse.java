package com.healthsoft.configs;

import lombok.Getter;

import java.util.List;

@Getter
public class FieldErrorResponse {
    private List<FieldError> fieldErrors;

    public FieldErrorResponse(List<FieldError> fieldErrorResources) {
        this.fieldErrors = fieldErrorResources;
    }
}