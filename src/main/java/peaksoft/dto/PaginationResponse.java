package peaksoft.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationResponse {
    private List<CustomerResponse> customerResponseList;
    private int totalPages;
    private long totalElements;

}
