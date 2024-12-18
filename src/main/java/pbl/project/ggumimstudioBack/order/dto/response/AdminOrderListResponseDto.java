package pbl.project.ggumimstudioBack.order.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;

import java.util.List;

@Data
public class AdminOrderListResponseDto
{
    private Long orderUID;
    private String orderId;
    private Long userUID;
    private String userId;
    private String paymentAmount;
    private OrderStatus orderStatus;
    private String createdAt;

    public AdminOrderListResponseDto(Order order, List<OrderItem> orderItemList)
    {
        this.orderUID = order.getOrderUID();
        this.orderId = order.getOrderId();
        this.userUID = order.getUser().getUserUID();
        this.userId = order.getUser().getUserId();
        this.paymentAmount = CommonUtil.formatNumberWithComma(getTotalPrice(orderItemList));
        this.orderStatus = order.getOrderStatus();
        this.createdAt = order.getCreatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDDHHMMSS(order.getCreatedAt()) : "-";
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
