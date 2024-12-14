package pbl.project.ggumimstudioBack.order.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderPaymentDetailResponseDto
{
    private String totalPrice;
    private List<OrderItemDto> orderItemList;

    @Data
    public static class OrderItemDto
    {
        private String productMainImg;
        private String productName;
        private Integer quantity;
        private String price;

        public OrderItemDto(OrderItem orderItem)
        {
            this.productMainImg = orderItem.getProduct().getProductMainImg();
            this.productName = orderItem.getProduct().getProductName();
            this.quantity = orderItem.getQuantity();
            this.price = CommonUtil.formatNumberWithComma(orderItem.getProduct().getProductPrice().intValue());
        }
    }

    public OrderPaymentDetailResponseDto(List<OrderItem> orderItemList)
    {
        this.totalPrice = CommonUtil.formatNumberWithComma(getTotalPrice(orderItemList));
        this.orderItemList = orderItemList.stream().map(OrderItemDto::new).toList();
    }

    private Integer getTotalPrice(List<OrderItem> orderItemList)
    {
        Integer totalPrice = 0;

        for (OrderItem item : orderItemList)
        {
            totalPrice += item.getProduct().getProductPrice().intValue() * item.getQuantity();
        }

        return totalPrice;
    }
}
