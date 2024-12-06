package pbl.project.ggumimstudioBack.product.repository;

import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductListResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.ProductListResponseDto;

@Repository
public interface ProductRepositoryCustom
{
    PaginationResponse<AdminProductListResponseDto> getAdminProductList(BaseSearchParamRequestDto searchParam);

    PaginationResponse<ProductListResponseDto> getProductList(BaseSearchParamRequestDto searchParam);
}
