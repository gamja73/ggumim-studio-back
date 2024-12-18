package pbl.project.ggumimstudioBack.order.dto.request;

import lombok.Data;

@Data
public class AdminOrderStatusUpdateRequestDto
{
    private Long orderUID;
    private String status;
}
