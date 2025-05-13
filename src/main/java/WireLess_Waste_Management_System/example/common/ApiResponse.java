package WireLess_Waste_Management_System.example.common;

import lombok.Getter;
import lombok.Setter;

/**
 * ApiResponse is a generic class that is used to standardize the response 
 * format for API responses across the application.
 * @param <T> the type of data that will be returned in the response.
 */
@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private int status;

    public ApiResponse(boolean success, T data, String message, int status) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.status = status;
    }
}