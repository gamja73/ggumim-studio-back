package pbl.project.ggumimstudioBack.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.order.dto.response.PaymentResponseDto;
import pbl.project.ggumimstudioBack.order.service.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentApiController
{
    private final PaymentService paymentService;

    @PostMapping("/{orderID}")
    public CommonApiResponse<PaymentResponseDto> payment(@PathVariable(name = "orderID") String orderID)
    {
        return CommonApiResponse.OK(paymentService.payment(orderID));
    }

    @PostMapping("/complete/{paymentID}")
    public CommonApiResponse<Boolean> paymentComplete(@PathVariable(name = "paymentID") String paymentID)
    {
        return CommonApiResponse.OK(paymentService.paymentComplete(paymentID));
    }
}
