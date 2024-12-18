package br.com.carpag.app.controllers.exceptions;

import br.com.carpag.app.dtos.generic.FieldErrorDto;
import br.com.carpag.app.dtos.generic.StandardResponseErrorDto;
import br.com.carpag.app.models.enums.ProblemType;
import br.com.carpag.app.services.exceptions.ResourceAlreadyExistsException;
import br.com.carpag.app.utils.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class CarPagExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardResponseErrorDto> handleEntityAlreadyExists(
            HttpServletRequest request,
            ResourceAlreadyExistsException exception){
        String pathUrl = HttpUtil.getRequestPath(request);
        Integer badRequest = HttpStatus.BAD_REQUEST.value();
        StandardResponseErrorDto standardResponseErrorDto = makeStandardResponseErrorDto(badRequest,
                "Entity already exists", ProblemType.RESOURCE_ALREADY_EXISTS, exception.getMessage(),
                pathUrl, new HashSet<>());
        return HttpUtil.makeResponseEntity(standardResponseErrorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponseErrorDto> handleValidation(HttpServletRequest request, MethodArgumentNotValidException exception){
        String pathUrl = HttpUtil.getRequestPath(request);
        Integer badRequest = HttpStatus.BAD_REQUEST.value();
        Set<FieldErrorDto> errors = new HashSet<>();
        exception.getFieldErrors().forEach(x -> {
            errors.add(new FieldErrorDto(x.getField(), x.getDefaultMessage()));
        });
        StandardResponseErrorDto standardResponseErrorDto = makeStandardResponseErrorDto(
                badRequest, "Hibernate Validation",
                ProblemType.INVALID_PARAM_ERROR, "Please check the errors field to validate the payload", pathUrl, errors);
        return HttpUtil.makeResponseEntity(standardResponseErrorDto, HttpStatus.BAD_REQUEST);
    }

    private static StandardResponseErrorDto makeStandardResponseErrorDto(Integer status, String error, ProblemType
            problemType, String message, String path, Set<FieldErrorDto> errors){
            return new StandardResponseErrorDto(Instant.now(), status, error, problemType, message, path
            ,errors);

    }


}
