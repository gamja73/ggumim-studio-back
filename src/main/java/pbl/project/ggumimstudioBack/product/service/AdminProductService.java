package pbl.project.ggumimstudioBack.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.product.dto.request.CreateProductRequestDto;
import pbl.project.ggumimstudioBack.product.dto.request.UpdateExposureRequestDto;
import pbl.project.ggumimstudioBack.product.dto.request.UpdateProductRequestDto;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductDetailResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductListResponseDto;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class AdminProductService
{
    private final ProductRepository productRepository;

    public String createProduct(CreateProductRequestDto requestDto)
    {
        productRepository.save(requestDto.toEntity());
        return "등록이 완료되었습니다.";
    }

    @Transactional
    public String updateProduct(UpdateProductRequestDto requestDto)
    {
        Product target = productRepository.findById(requestDto.getProductUID())
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        target.update(requestDto);

        return "수정이 완료되었습니다.";
    }

    @Transactional
    public String updateProductExposure(UpdateExposureRequestDto requestDto)
    {
        Product target = productRepository.findById(requestDto.getProductUID())
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        target.changeExposure(requestDto.getIsExposure());

        return "노출 상태 변경이 완료되었습니다.";
    }

    public AdminProductDetailResponseDto getProductDetail(Long productUID)
    {
        Product target = productRepository.findById(productUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        return new AdminProductDetailResponseDto(target);
    }

    public PaginationResponse<AdminProductListResponseDto> getAdminProductList(BaseSearchParamRequestDto searchParam)
    {
        return productRepository.getAdminProductList(searchParam);
    }
}
