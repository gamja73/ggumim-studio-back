package pbl.project.ggumimstudioBack.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment extends BaseEntity
{
    @Id
    @Column(name = "payment_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentUID;

    @Comment("결제 ID")
    @Column(name = "payment_id")
    private String paymentId;

    @Comment("결제 여부")
    @Column(name = "is_payment")
    private Boolean isPayment;

    @Comment("결제 데이터")
    @Column(name = "payment_request_data", columnDefinition = "TEXT")
    private String paymentRequestData;

    @Comment("결제 데이터")
    @Column(name = "payment_response_data", columnDefinition = "TEXT")
    private String paymentResponseData;

    public Payment(String paymentId, String requestData)
    {
        this.paymentId = paymentId;
        this.paymentRequestData = requestData;
        this.isPayment = false;
    }
}
