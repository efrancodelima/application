package br.com.fiap.techchallenge.interfacelayer.exceptionhandler;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {

    private int code;
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
