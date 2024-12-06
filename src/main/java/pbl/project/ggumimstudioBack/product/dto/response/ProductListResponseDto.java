package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Data
public class ProductListResponseDto
{
    private Long productUID;
    private String productName;
    private String productPrice;
    private String createdAt;
    private String updatedAt;

    public ProductListResponseDto(Product product)
    {
        this.productUID = product.getProductUID();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice().toString();
        this.createdAt = product.getCreatedAt().toString();
        this.updatedAt = product.getUpdatedAt().toString();
    }
}
