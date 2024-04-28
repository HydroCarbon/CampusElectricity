package io.hydrocarbon.campus.electricity.param.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class Response<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> failed(T data) {
        Response<T> response = new Response<>();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> status(Integer code, T data) {
        HttpStatus httpStatus = HttpStatus.resolve(code);
        return status(httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus, data);
    }

    public static <T> Response<T> status(HttpStatus status, T data) {
        Response<T> response = new Response<>();
        response.setCode(status.value());
        response.setMessage(status.getReasonPhrase());
        response.setData(data);
        return response;
    }
}
