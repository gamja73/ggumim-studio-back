package pbl.project.ggumimstudioBack.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;
import pbl.project.ggumimstudioBack.product.dto.response.ProductDetailResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.ProductListResponseDto;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public PaginationResponse<ProductListResponseDto> getProductList(BaseSearchParamRequestDto searchParam)
    {
        try
        {
            Long userUID = jwtUtil.getUserUID();

            if (userUID == null)
            {
                throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
            }

            User user = userRepository.findById(userUID)
                    .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

            return productRepository.getProductLisForUser(searchParam, user);
        }
        catch (Exception e)
        {
            return productRepository.getProductList(searchParam);
        }
    }

    public ProductDetailResponseDto getProductDetail(Long productUID)
    {
        Product target = productRepository.findById(productUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        return new ProductDetailResponseDto(target);
    }
}
