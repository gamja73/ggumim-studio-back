package pbl.project.ggumimstudioBack.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaginationResponse<T>
{
    private int totalCount;
    private int currentPage;
    private int totalPages;
    private List<T> itemList;
}
