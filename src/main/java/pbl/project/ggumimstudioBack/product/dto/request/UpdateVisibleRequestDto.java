package pbl.project.ggumimstudioBack.product.dto.request;

import lombok.Data;

@Data
public class UpdateVisibleRequestDto
{
    private Long productUID;
    private Boolean isVisible;
}
