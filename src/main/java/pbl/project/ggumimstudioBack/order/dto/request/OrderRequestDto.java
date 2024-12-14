package pbl.project.ggumimstudioBack.order.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto
{
    private List<OrderItemDto> orderItemList;

    @Data
    public static class OrderItemDto
    {
        private Long productUID;
        private Integer quantity;
    }
}
