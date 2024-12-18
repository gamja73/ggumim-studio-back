package pbl.project.ggumimstudioBack.order.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.dto.response.AdminOrderDetailResponseDto;
import pbl.project.ggumimstudioBack.order.dto.response.AdminOrderListResponseDto;
import pbl.project.ggumimstudioBack.order.service.AdminOrderService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/order")
public class AdminOrderViewController
{
    private final AdminOrderService adminOrderService;

    @GetMapping("/{orderUID}")
    public String adminOrderDetail(@PathVariable("orderUID") Long orderUID, Model model)
    {
        AdminOrderDetailResponseDto pageData = adminOrderService.getAdminOrderDetail(orderUID);

        model.addAttribute("pageData", pageData);
        model.addAttribute("orderStatus", OrderStatus.values());

        return "order/orderDetail";
    }

    @GetMapping("/list")
    public String adminOrderList(BaseSearchParamRequestDto searchParam, Model model)
    {
        PaginationResponse<AdminOrderListResponseDto> pageData = adminOrderService.getAdminOrderList(searchParam);

        model.addAttribute("totalCount", pageData.getTotalCount());
        model.addAttribute("totalPages", pageData.getTotalPages());
        model.addAttribute("currentPage", pageData.getCurrentPage());
        model.addAttribute("currentGroupPage", (searchParam.getPage() - 1) / 5 + 1);
        model.addAttribute("paginationLimit", 5);
        model.addAttribute("page", searchParam.getPage());
        model.addAttribute("limit", searchParam.getLimit());
        model.addAttribute("searchType", searchParam.getSearchType());
        model.addAttribute("searchValue", searchParam.getSearchValue());
        model.addAttribute("startAt", searchParam.getStartAt());
        model.addAttribute("endAt", searchParam.getEndAt());

        model.addAttribute("pageData", pageData);
        model.addAttribute("pageUrl", "/admin/order/list");

        return "order/orderList";
    }
}
