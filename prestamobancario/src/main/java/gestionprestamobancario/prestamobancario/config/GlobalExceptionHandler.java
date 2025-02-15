package gestionprestamobancario.prestamobancario.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerException(NullPointerException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> numberFormatException(NumberFormatException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    // Manejar el error de deserialización para BigDecimal
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "Entrada no válida.";

        // Retornar una respuesta con un mensaje más claro y un estado HTTP 400 (Bad Request)
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException exception) {
        Map<String, String> errores = new HashMap<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String parametro = ((FieldError) error).getField();
                            String mensaje = error.getDefaultMessage();
                            errores.put(parametro, mensaje);                    }
                );
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
