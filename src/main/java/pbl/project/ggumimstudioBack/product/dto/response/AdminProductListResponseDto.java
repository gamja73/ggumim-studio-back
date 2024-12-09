package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Data
public class AdminProductListResponseDto
{
    private Long productUID;
    private String productName;
    private String productPrice;
    private String createdAt;
    private String updatedAt;
    private Boolean isExposure;

    public AdminProductListResponseDto(Product product)
    {
        this.productUID = product.getProductUID();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice().toString();
        this.createdAt = product.getCreatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDD(product.getCreatedAt()) : "-";
        this.updatedAt = product.getUpdatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDD(product.getUpdatedAt()) : "-";
        this.isExposure = product.getIsExposure();
    }
}
