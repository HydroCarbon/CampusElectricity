package io.hydrocarbon.campus.electricity.config;

import io.hydrocarbon.campus.electricity.param.response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<ProblemDetail>> handleSecurityException(Exception ex) {
        ProblemDetail errorDetail;
        switch (ex) {
            case BadCredentialsException ignored ->
                    errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            case ExpiredJwtException ignored ->
                    errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            case UsernameNotFoundException ignored ->
                    errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
            default -> errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
        }

        Response<ProblemDetail> result = Response.status(errorDetail.getStatus(), errorDetail);
        return ResponseEntity.ok(result);
    }
}
