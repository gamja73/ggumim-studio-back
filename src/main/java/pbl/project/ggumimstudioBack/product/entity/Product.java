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
    @Column(name = "product_main_img", columnDefinition = "TEXT")
    private String productMainImg;

    @Comment("상품명")
    @Column(name = "product_name")
    private String productName;

    @Comment("상품 설명")
    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Comment("상품 가격")
    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Comment("상품 카테고리")
    @Column(name = "product_category")
    private String productCategory;

    @Comment("상품 상세")
    @Column(name = "product_detail", columnDefinition = "TEXT")
    private String productDetail;

    @Comment("노출 여부")
    @Column(name = "is_visible")
    private Boolean isVisible;

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
        if (requestDto.getProductDescription() != null && !requestDto.getProductDescription().isBlank())
        {
            this.productDescription = requestDto.getProductDescription();
        }
        if (requestDto.getProductPrice() != null)
        {
            this.productPrice = requestDto.getProductPrice();
        }
        if (requestDto.getProductCategory() != null && !requestDto.getProductCategory().isBlank())
        {
            this.productCategory = requestDto.getProductCategory();
        }
        if (requestDto.getProductDetailEditor() != null && !requestDto.getProductDetailEditor().isBlank())
        {
            this.productDetail = requestDto.getProductDetailEditor();
        }
        if (requestDto.getIsVisible() != null)
        {
            this.isVisible = requestDto.getIsVisible();
        }
    }

    public void changeVisible(Boolean isVisible)
    {
        this.isVisible = isVisible;
    }
}
