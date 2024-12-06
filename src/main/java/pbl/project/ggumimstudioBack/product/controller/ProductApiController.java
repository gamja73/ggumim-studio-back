package pbl.project.ggumimstudioBack.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductDetailResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductListResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.ProductDetailResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.ProductListResponseDto;
import pbl.project.ggumimstudioBack.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductApiController
{

    private final ProductService productService;

    @GetMapping("/{productUID}")
    public CommonApiResponse<ProductDetailResponseDto> productDetail(@PathVariable("productUID") Long productUID)
    {
        return CommonApiResponse.OK(productService.getProductDetail(productUID));
    }

    @GetMapping("/list")
    public CommonApiResponse<PaginationResponse<ProductListResponseDto>> productList(BaseSearchParamRequestDto searchParam)
    {
        return CommonApiResponse.OK(productService.getProductList(searchParam));
    }
}
