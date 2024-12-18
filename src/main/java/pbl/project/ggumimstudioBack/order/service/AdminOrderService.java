package pbl.project.ggumimstudioBack.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.dto.request.AdminOrderStatusUpdateRequestDto;
import pbl.project.ggumimstudioBack.order.dto.response.AdminOrderDetailResponseDto;
import pbl.project.ggumimstudioBack.order.dto.response.AdminOrderListResponseDto;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;
import pbl.project.ggumimstudioBack.order.repository.OrderItemRepository;
import pbl.project.ggumimstudioBack.order.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOrderService
{
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public String updateOrderStatus(AdminOrderStatusUpdateRequestDto requestDto)
    {
        Order order = orderRepository.findById(requestDto.getOrderUID())
                .orElseThrow(() -> new CustomException(CustomErrorCode.ORDER_NOT_FOUND));

        order.updateStatus(OrderStatus.valueOf(requestDto.getStatus()));

        return "변경이 완료되었습니다.";
    }

    public AdminOrderDetailResponseDto getAdminOrderDetail(Long orderUID)
    {
        Order order = orderRepository.findById(orderUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.ORDER_NOT_FOUND));

        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);

        return new AdminOrderDetailResponseDto(order, orderItemList, objectMapper);
    }

    public PaginationResponse<AdminOrderListResponseDto> getAdminOrderList(BaseSearchParamRequestDto searchParam)
    {
        return orderRepository.getAdminOrderList(searchParam);
    }
}