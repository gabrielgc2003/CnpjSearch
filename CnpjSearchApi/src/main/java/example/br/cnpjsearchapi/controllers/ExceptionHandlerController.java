package example.br.cnpjsearchapi.controllers;

import example.br.cnpjsearchapi.exceptions.ApiException;
import example.br.cnpjsearchapi.exceptions.ApiExceptionDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Controlador de exceções global para lidar com exceções de API e validação
@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * Manipulador para exceções personalizadas de API.
     *
     * @param e Exceção personalizada de API.
     * @return Detalhes da exceção em formato DTO.
     */
    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiExceptionDTO handleApiException(ApiException e) {
        return new ApiExceptionDTO(e.getMessage());
    }

    /**
     * Manipulador para exceções de validação de dados.
     *
     * @param e Exceção de validação.
     * @return Detalhes da exceção de validação em formato DTO.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleValidationExceptions(Exception e) {
        String errorMessage;

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            errorMessage = exception.getBindingResult().getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                    .orElse("Invalid input");
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) e;
            errorMessage = exception.getConstraintViolations().stream()
                    .map(violation -> violation.getMessage())
                    .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                    .orElse("Invalid input");
        } else {
            errorMessage = "Validation error";
        }

        return new ApiExceptionDTO(errorMessage);
    }

    /**
     * Manipulador para exceções genéricas.
     *
     * @param e Exceção não tratada.
     * @return Detalhes da exceção genérica em formato DTO.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiExceptionDTO handleException(Exception e) {
        return new ApiExceptionDTO("Ocorreu um erro inesperado: " + e.getMessage());
    }
}
