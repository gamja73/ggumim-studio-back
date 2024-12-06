package pbl.project.ggumimstudioBack.product.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.product.entity.Product;

import java.util.List;

@Data
public class ProductDetailResponseDto
{
    private Long productUID;
    private String productMainImg;
    private String productName;
    private String productPrice;
    private String productCategory;
    private List<String> productColorOptionList;
    private List<String> productSizeOptionList;
    private String productDetail;

    public ProductDetailResponseDto(Product product)
    {
        this.productUID = product.getProductUID();
        this.productMainImg = product.getProductMainImg();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice().intValue() + "";
        this.productCategory = product.getProductCategory();
        this.productColorOptionList = product.getProductColorOptionList();
        this.productSizeOptionList = product.getProductSizeOptionList();
        this.productDetail = product.getProductDetail();
    }
}
