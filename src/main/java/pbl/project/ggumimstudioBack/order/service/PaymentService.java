package pbl.project.ggumimstudioBack.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.order.dto.response.PaymentResponseDto;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;
import pbl.project.ggumimstudioBack.order.entity.Payment;
import pbl.project.ggumimstudioBack.order.repository.OrderItemRepository;
import pbl.project.ggumimstudioBack.order.repository.OrderRepository;
import pbl.project.ggumimstudioBack.order.repository.PaymentRepository;
import pbl.project.ggumimstudioBack.order.repository.PaymentResultResponseDto;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService
{
    @Value("${portOne.storeId}")
    private String storeId;

    @Value("${portOne.channelKey}")
    private String channelKey;

    @Value("${portOne.apiSecret}")
    private String apiSecret;

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public PaymentResponseDto payment(String orderID)
    {
        Order order = orderRepository.findByOrderID(orderID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.ORDER_NOT_FOUND));

        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);

        PaymentResponseDto responseDto = PaymentResponseDto.createPayment(orderID, storeId, channelKey, getOrderName(orderItemList), getTotalPrice(orderItemList).longValue());

        try
        {
            Payment newPayment = paymentRepository.save(new Payment(orderID, objectMapper.writeValueAsString(responseDto)));
            order.setPayment(newPayment);
        }
        catch (JsonProcessingException e)
        {
            Payment newPayment = paymentRepository.save(new Payment(orderID, ""));
            order.setPayment(newPayment);
        }

        return responseDto;
    }

    private String getOrderName(List<OrderItem> orderItemList)
    {
        if (orderItemList.size() == 1)
        {
            return orderItemList.get(0).getProduct().getProductName();
        }
        else if (orderItemList.size() > 1)
        {
            return orderItemList.get(0).getProduct().getProductName() + " 외" + (orderItemList.size() - 1) + "건";
        }

        throw new CustomException(CustomErrorCode.ORDER_PRODUCT_NOT_FOUND);
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

    public boolean paymentComplete(String paymentID)
    {
        WebClient webClient = webClientBuilder.baseUrl("https://api.portone.io").build();

        Map<String, String> map = Map.of("paymentId", paymentID);

        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/payments/" + paymentID)
                        .queryParam("storeId", storeId)
                        .build())
                .header("Content-Type", "application/json")
                .header("Authorization", "PortOne " + apiSecret)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        PaymentResultResponseDto result = null;

        try
        {
            result = objectMapper.readValue(response, PaymentResultResponseDto.class);

            if (result.getStatus().equals("PAID"))
            {
                return true;
            }
        }
        catch (JsonProcessingException e)
        {}

        return false;
    }
}
