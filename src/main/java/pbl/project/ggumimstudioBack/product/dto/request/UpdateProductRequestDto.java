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
    private BigDecimal productPrice;
    private String productCategory;
    private List<String> productColorOptionList;
    private List<String> productSizeOptionList;
    private String productDetailEditor;
    private Boolean isExposure;
}
