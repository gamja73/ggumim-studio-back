package pbl.project.ggumimstudioBack.product.dto.request;

import lombok.Data;

@Data
public class UpdateExposureRequestDto
{
    private Long productUID;
    private Boolean isExposure;
}
