package pbl.project.ggumimstudioBack.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.product.entity.Product;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem extends BaseEntity
{
    @Id
    @Column(name = "order_item_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemUID;

    @ManyToOne
    @JoinColumn(name = "order_uid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_uid")
    private Product product;

    @Comment("상품 개수")
    @Column(name = "quantity")
    private Integer quantity;
}
