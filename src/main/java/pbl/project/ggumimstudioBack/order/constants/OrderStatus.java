package pbl.project.ggumimstudioBack.order.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus
{
    RECEIVED("주문 접수"),
    PAYMENT_COMPLETED("결제 완료"),
    PREPARING("상품 준비 중"),
    SHIPPING_PREPARING("배송 준비 중"),
    SHIPPED("배송 중"),
    DELIVERED("배송 완료"),
    ;

    private final String orderStatusText;
}
