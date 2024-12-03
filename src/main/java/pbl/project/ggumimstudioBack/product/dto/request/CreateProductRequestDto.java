package pbl.project.ggumimstudioBack.product.dto.request;

import lombok.Data;
import pbl.project.ggumimstudioBack.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateProductRequestDto
{
    private String productName;
    private BigDecimal productPrice;
    private String productCategory;
    private List<String> productColorOptionList;
    private List<String> productSizeOptionList;
    private String productDetailEditor;

    public Product toEntity()
    {
        return Product.builder()
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productCategory(this.productCategory)
                .productColorOptionList(this.productColorOptionList)
                .productSizeOptionList(this.productSizeOptionList)
                .productDetail(this.productDetailEditor)
                .build();
    }
}
