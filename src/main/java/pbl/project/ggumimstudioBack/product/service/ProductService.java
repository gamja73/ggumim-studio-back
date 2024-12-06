package pbl.project.ggumimstudioBack.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.product.dto.response.ProductDetailResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.ProductListResponseDto;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;

    public PaginationResponse<ProductListResponseDto> getProductList(BaseSearchParamRequestDto searchParam)
    {
        return productRepository.getProductList(searchParam);
    }

    public ProductDetailResponseDto getProductDetail(Long productUID)
    {
        Product target = productRepository.findById(productUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        return new ProductDetailResponseDto(target);
    }
}
