package pbl.project.ggumimstudioBack.product.dto.request;

import lombok.Data;
import pbl.project.ggumimstudioBack.product.entity.Product;

import java.math.BigDecimal;

@Data
public class CreateProductRequestDto
{
    private String productMainImg;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productCategory;
    private String productDetailEditor;

    public Product toEntity()
    {
        return Product.builder()
                .productMainImg(this.productMainImg)
                .productName(this.productName)
                .productDescription(this.productDescription)
                .productPrice(this.productPrice)
                .productCategory(this.productCategory)
                .productDetail(this.productDetailEditor)
                .isVisible(false)
                .build();
    }
}
