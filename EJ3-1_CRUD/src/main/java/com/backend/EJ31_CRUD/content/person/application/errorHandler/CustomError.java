package com.backend.EJ31_CRUD.content.person.application.errorHandler;

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
        return String.format("Fecha Excepcion: %tY-%<tm-%<td%nCodigo Http: %d%nMensaje: %S", timestamp, httpCode, message);
    }
}
