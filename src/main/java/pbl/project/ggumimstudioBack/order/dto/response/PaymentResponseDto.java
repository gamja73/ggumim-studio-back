package pbl.project.ggumimstudioBack.order.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDto
{
    private String paymentId;   // 결제 아이디
    private String storeId;     // 상점 아이디
    private String channelKey;  // 채널 아이디
    private String orderName;   // 상품명
    private Long totalAmount; // 주문 금액
    private String currency;    // 통화
    private String payMethod;   // 결제방식

    public static PaymentResponseDto createPayment(String paymentId, String storeId, String channelKey, String orderName, Long totalAmount)
    {
        return PaymentResponseDto.builder()
                .paymentId(paymentId)
                .storeId(storeId)
                .channelKey(channelKey)
                .orderName(orderName)
                .totalAmount(totalAmount)
                .currency("KRW")
                .payMethod("CARD")
                .build();
    }
}
