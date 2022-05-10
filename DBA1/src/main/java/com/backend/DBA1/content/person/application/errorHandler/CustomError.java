package com.backend.DBA1.content.person.application.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CustomError {
    Date timestamp;
    int httpCode;
    String message;

    @Override
    public String toString() {
        return String.format("%s %tY-%<tm-%<td%n","Representación completa de la fecha: %d %S", timestamp, httpCode, message);
    }
}
