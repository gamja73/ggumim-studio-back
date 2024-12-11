package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Data
public class ProductListResponseDto
{
    private Long productUID;
    private String productName;
    private String productPrice;
    private String productMainImg;

    public ProductListResponseDto(Product product)
    {
        this.productUID = product.getProductUID();
        this.productName = product.getProductName();
        this.productMainImg = product.getProductMainImg();
        this.productPrice = product.getProductPrice() != null ? CommonUtil.formatNumberWithComma(product.getProductPrice().intValue()) : "-";
    }
}
