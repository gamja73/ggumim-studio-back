package pbl.project.ggumimstudioBack.order.repository;

import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.order.dto.response.AdminOrderListResponseDto;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.util.List;

public interface OrderRepositoryCustom
{
    PaginationResponse<AdminOrderListResponseDto> getUserOrderList(BaseSearchParamRequestDto searchParam, User user);
    PaginationResponse<AdminOrderListResponseDto> getAdminOrderList(BaseSearchParamRequestDto searchParam);
    List<Order> getDashBoardOrderMonth();
    List<Order> getDashBoardOrderDay();
}
