package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Data
public class AdminProductDetailResponseDto
{
    private Long productUID;
    private String productMainImg;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productCategory;
    private String productDetail;
    private Boolean isVisible;

    public AdminProductDetailResponseDto(Product product)
    {
        this.productUID = product.getProductUID();
        this.productMainImg = product.getProductMainImg();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = product.getProductPrice().intValue() + "";
        this.productCategory = product.getProductCategory();
        this.productDetail = product.getProductDetail();
        this.isVisible = product.getIsVisible();
    }
}
