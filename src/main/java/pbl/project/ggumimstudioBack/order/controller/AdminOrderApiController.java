package pbl.project.ggumimstudioBack.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.order.dto.request.AdminOrderStatusUpdateRequestDto;
import pbl.project.ggumimstudioBack.order.service.AdminOrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/order")
public class AdminOrderApiController
{
    private final AdminOrderService adminOrderService;

    @PutMapping("/status")
    public CommonApiResponse<String> updateOrderStatus(@RequestBody AdminOrderStatusUpdateRequestDto requestDto)
    {
        return CommonApiResponse.OK(adminOrderService.updateOrderStatus(requestDto));
    }
}
