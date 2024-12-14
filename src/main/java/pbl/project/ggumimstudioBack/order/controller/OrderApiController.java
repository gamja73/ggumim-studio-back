package pbl.project.ggumimstudioBack.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.order.dto.request.OrderRequestDto;
import pbl.project.ggumimstudioBack.order.dto.response.OrderPaymentDetailResponseDto;
import pbl.project.ggumimstudioBack.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderApiController
{
    private final OrderService orderService;

    @PostMapping("")
    public CommonApiResponse<String> order(@RequestBody OrderRequestDto requestDto)
    {
        return CommonApiResponse.OK(orderService.order(requestDto));
    }

    @GetMapping("/{orderID}")
    public CommonApiResponse<OrderPaymentDetailResponseDto> getOrderDetailPageData(@PathVariable(name = "orderID") String orderID)
    {
        return CommonApiResponse.OK(orderService.getOrderDetailPageData(orderID));
    }
}
