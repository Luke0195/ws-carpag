package br.com.carpag.app.models.enums;

import lombok.Getter;

@Getter
public enum ProblemType {
    INVALID_PARAM_ERROR("INVALID PARAM ERROR"),
    MISSING_PARAM_ERROR("MISSING PARAM ERROR"),
    SERVER_ERROR("SERVER ERROR"),
    RESOURCE_ALREADY_EXISTS("RESOURCE ALREADY EXISTS"),
    BUSINESS_ERROR("BUSINESS_ERROR");

    private String title;

    ProblemType(String title){
        this.title = title;
    }

}
