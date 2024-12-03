package pbl.project.ggumimstudioBack.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.converter.StringListConverter;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;

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
}
