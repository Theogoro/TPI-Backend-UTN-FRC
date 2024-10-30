package com.backend.empleados.exceptions;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public ErrorResponse(Exception ex, int status) {
        this.message = ex.getMessage();
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
