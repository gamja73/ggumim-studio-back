package pbl.project.ggumimstudioBack.product.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateProductRequestDto
{
    private Long productUID;
    private String productMainImg;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productCategory;
    private String productDetailEditor;
    private Boolean isVisible;
}
