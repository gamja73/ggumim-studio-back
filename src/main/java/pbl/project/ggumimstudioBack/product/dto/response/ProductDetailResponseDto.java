package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Data
public class ProductDetailResponseDto
{
    private Long productUID;
    private String productMainImg;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productCategory;
    private String productDetail;

    public ProductDetailResponseDto(Product product)
    {
        this.productUID = product.getProductUID();
        this.productMainImg = product.getProductMainImg();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = CommonUtil.formatNumberWithComma(product.getProductPrice().intValue());
        this.productCategory = product.getProductCategory();
        this.productDetail = product.getProductDetail();
    }
}
