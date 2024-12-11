package pbl.project.ggumimstudioBack.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.product.dto.request.CreateProductRequestDto;
import pbl.project.ggumimstudioBack.product.dto.request.UpdateProductRequestDto;
import pbl.project.ggumimstudioBack.product.dto.request.UpdateVisibleRequestDto;
import pbl.project.ggumimstudioBack.product.service.AdminProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/product")
public class AdminProductApiController
{
    private final AdminProductService adminProductService;

    @PostMapping("")
    public CommonApiResponse<String> createProduct(@RequestBody CreateProductRequestDto requestDto)
    {
        return CommonApiResponse.OK(adminProductService.createProduct(requestDto));
    }

    @PutMapping("")
    public CommonApiResponse<String> updateProduct(@RequestBody UpdateProductRequestDto requestDto)
    {
        return CommonApiResponse.OK(adminProductService.updateProduct(requestDto));
    }

    @PutMapping("/exposure")
    public CommonApiResponse<String> createProduct(@RequestBody UpdateVisibleRequestDto requestDto)
    {
        return CommonApiResponse.OK(adminProductService.updateProductVisible(requestDto));
    }
}
