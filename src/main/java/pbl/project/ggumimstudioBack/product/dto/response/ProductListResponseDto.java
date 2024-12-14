package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Builder;
import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Data
@Builder
public class ProductListResponseDto
{
    private Long productUID;
    private String productName;
    private String productPrice;
    private String productMainImg;
    private Boolean wishList;
    private Boolean shoppingCart;

    public static ProductListResponseDto forNotUser(Product product)
    {
        return ProductListResponseDto.builder()
                .productUID(product.getProductUID())
                .productName(product.getProductName())
                .productMainImg(product.getProductMainImg())
                .productPrice(product.getProductPrice() != null ? CommonUtil.formatNumberWithComma(product.getProductPrice().intValue()) : "-")
                .wishList(false)
                .shoppingCart(false)
                .build();
    }

    public static ProductListResponseDto forUser(Product product, Long wishListCount, Long shoppingCartCount)
    {
        return ProductListResponseDto.builder()
                .productUID(product.getProductUID())
                .productName(product.getProductName())
                .productMainImg(product.getProductMainImg())
                .productPrice(product.getProductPrice() != null ? CommonUtil.formatNumberWithComma(product.getProductPrice().intValue()) : "-")
                .wishList(wishListCount != null && wishListCount >= 1)
                .shoppingCart(shoppingCartCount != null && shoppingCartCount >= 1)
                .build();
    }
}
