package pbl.project.ggumimstudioBack.common.dto.request;

import lombok.Data;

@Data
public class BaseSearchParamRequestDto
{
    private String searchType;
    private String searchValue;
    private String startAt;
    private String endAt;
    private Integer page = 1;
    private Integer limit = 10;
}
