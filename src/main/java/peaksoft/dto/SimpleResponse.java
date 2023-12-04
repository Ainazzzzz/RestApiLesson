package peaksoft.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class SimpleResponse {
    private HttpStatus status;
    private String message;



    public SimpleResponse(String message,HttpStatus status) {
        this.status = status;
        this.message = message;
    }

}
