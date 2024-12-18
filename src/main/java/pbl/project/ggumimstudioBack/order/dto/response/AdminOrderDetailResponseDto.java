package pbl.project.ggumimstudioBack.order.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;

import java.util.List;

@Data
public class AdminOrderDetailResponseDto
{
    private Long orderUID;
    private String orderId;
    private String userId;
    private String paymentAmount;
    private OrderStatus orderStatus;
    private List<OrderItemResponseDto> orderItemList;
    private PaymentResultResponseDto paymentResponse;
    private String createdAt;

    @Data
    public static class OrderItemResponseDto
    {
        private Long productUID;
        private String productMainImg;
        private String productName;
        private String productPrice;
        private Long quantity;

        public OrderItemResponseDto(OrderItem orderItem)
        {
            this.productUID = orderItem.getProduct().getProductUID();
            this.productMainImg = orderItem.getProduct().getProductMainImg();
            this.productName = orderItem.getProduct().getProductName();
            this.productPrice = CommonUtil.formatNumberWithComma(orderItem.getProduct().getProductPrice().intValue());
            this.quantity = orderItem.getQuantity().longValue();
        }
    }

    public AdminOrderDetailResponseDto(Order order, List<OrderItem> orderItemList, ObjectMapper objectMapper)
    {
        this.orderUID = order.getOrderUID();
        this.orderId = order.getOrderId();
        this.userId = order.getUser().getUserId();
        this.paymentAmount = CommonUtil.formatNumberWithComma(getTotalPrice(orderItemList));
        this.orderStatus = order.getOrderStatus();
        this.orderItemList = orderItemList.stream().map(OrderItemResponseDto::new).toList();
        this.createdAt = CommonUtil.localDateTimeFormatToYYYYMMDDHHMMSS(order.getCreatedAt());

        try
        {
            this.paymentResponse = objectMapper.readValue(order.getPayment().getPaymentResponseData(), PaymentResultResponseDto.class);
        }
        catch (Exception e) {}
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
