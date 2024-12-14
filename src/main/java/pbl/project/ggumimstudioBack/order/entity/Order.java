package pbl.project.ggumimstudioBack.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity
{
    @Id
    @Column(name = "order_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderUID;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private User user;

    @Comment("주문번호")
    @Column(name = "order_id")
    private String orderId;

    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Setter
    @Comment("결제")
    @JoinColumn(name = "payment_uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @Comment("배송 완료 일시")
    @Column(name = "delivery_completed_at")
    private LocalDateTime deliveryCompletedAt;

}
