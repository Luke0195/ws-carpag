package br.com.carpag.app.dtos.generic;

import br.com.carpag.app.models.enums.ProblemType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Set;

public record StandardResponseErrorDto(
        Instant timestamp,
        Integer status,
        String error,
        @JsonProperty("problem_type")
        ProblemType problemType,
        String message,
        @JsonProperty("path_url")
        String path,
        Set<FieldErrorDto> errors){
}
