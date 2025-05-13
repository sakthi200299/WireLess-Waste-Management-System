package WireLess_Waste_Management_System.example.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import WireLess_Waste_Management_System.example.common.ApiResponse;

import org.springframework.validation.FieldError;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler that catches all exceptions across controllers
 * and returns a standardized response.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle general exceptions (fallback for unhandled exceptions).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(false, null,
                "An error occurred: " + exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle validation errors for method arguments (e.g., missing or invalid request parameters).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getBindingResult().getFieldErrors()
                                              .stream()
                                              .map(FieldError::getDefaultMessage)
                                              .collect(Collectors.toList());
        ApiResponse<Void> apiResponse = new ApiResponse<>(false, null,
                errorMessages.isEmpty() ? "Validation failed" : errorMessages.get(0),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}