package pbl.project.ggumimstudioBack.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductDetailResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductListResponseDto;
import pbl.project.ggumimstudioBack.product.service.AdminProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductViewController
{
    private final AdminProductService adminProductService;

    @GetMapping("")
    public String createAdminProduct()
    {
        return "product/productCreateForm";
    }

    @GetMapping("/{productUID}")
    public String adminProductDetail(@PathVariable("productUID") Long productUID, Model model)
    {
        AdminProductDetailResponseDto pageData = adminProductService.getProductDetail(productUID);

        model.addAttribute("pageData", pageData);

        return "product/productDetail";
    }

    @GetMapping("/list")
    public String adminProductList(BaseSearchParamRequestDto searchParam, Model model)
    {
        PaginationResponse<AdminProductListResponseDto> pageData = adminProductService.getAdminProductList(searchParam);

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
        model.addAttribute("pageUrl", "/admin/product/list");

        return "product/productList";
    }
}
