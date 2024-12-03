package pbl.project.ggumimstudioBack.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.product.dto.request.CreateProductRequestDto;
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
}
