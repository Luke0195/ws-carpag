package br.com.carpag.app.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class HttpUtil {

    private HttpUtil(){};

    public static String getRequestPath(HttpServletRequest request){
        return request.getRequestURI();
    }

    public static URI getUriFromRequest(Object object){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object).toUri();
    }


    public static <T> ResponseEntity<T> makeResponseEntity(T body, HttpStatus status){
        return ResponseEntity.status(status).body(body);
    }

    public static <T,K> ResponseEntity<T> created(T body, K id){
        URI uri = getUriFromRequest(id);
        return ResponseEntity.created(uri).body(body);
    }


}
