package pbl.project.ggumimstudioBack.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.converter.StringListConverter;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;
import pbl.project.ggumimstudioBack.product.dto.request.UpdateProductRequestDto;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity
{
    @Id
    @Column(name = "product_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productUID;

    @Comment("상품 메인 이미지")
    @Column(name = "product_main_img")
    private String productMainImg;

    @Comment("상품명")
    @Column(name = "product_name")
    private String productName;

    @Comment("상품 가격")
    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Comment("상품 카테고리")
    @Column(name = "product_category")
    private String productCategory;

    @Comment("상품 색상 옵션")
    @Column(name = "product_color_option")
    @Convert(converter = StringListConverter.class)
    private List<String> productColorOptionList;

    @Comment("상품 사이즈 옵션")
    @Column(name = "product_size_option")
    @Convert(converter = StringListConverter.class)
    private List<String> productSizeOptionList;

    @Comment("상품 상세")
    @Column(name = "product_detail")
    private String productDetail;

    @Comment("노출 여부")
    @Column(name = "is_exposure")
    private Boolean isExposure;

    public void update(UpdateProductRequestDto requestDto)
    {
        if (requestDto.getProductMainImg() != null && !requestDto.getProductMainImg().isBlank())
        {
            this.productMainImg = requestDto.getProductMainImg();
        }
        if (requestDto.getProductName() != null && !requestDto.getProductName().isBlank())
        {
            this.productName = requestDto.getProductName();
        }
        if (requestDto.getProductPrice() != null)
        {
            this.productPrice = requestDto.getProductPrice();
        }
        if (requestDto.getProductCategory() != null && !requestDto.getProductCategory().isBlank())
        {
            this.productCategory = requestDto.getProductCategory();
        }
        if (requestDto.getProductColorOptionList() != null && !requestDto.getProductColorOptionList().isEmpty())
        {
            this.productColorOptionList = requestDto.getProductColorOptionList();
        }
        if (requestDto.getProductSizeOptionList() != null && !requestDto.getProductSizeOptionList().isEmpty())
        {
            this.productSizeOptionList = requestDto.getProductSizeOptionList();
        }
        if (requestDto.getProductDetailEditor() != null && !requestDto.getProductDetailEditor().isBlank())
        {
            this.productDetail = requestDto.getProductDetailEditor();
        }
        if (requestDto.getIsExposure() != null)
        {
            this.isExposure = requestDto.getIsExposure();
        }
    }

    public void changeExposure(Boolean isExposure)
    {
        this.isExposure = isExposure;
    }
}
