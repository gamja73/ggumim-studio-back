package pbl.project.ggumimstudioBack.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Shopping_cart")
public class ShoppingCart extends BaseEntity
{
    @Id
    @Column(name = "shopping_cart_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartUID;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_uid")
    private Product product;

    public ShoppingCart(User user, Product product)
    {
        this.user = user;
        this.product = product;
    }

    public void delete()
    {
        this.setIsDeleted(true);
    }
}
