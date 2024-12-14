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
@Table(name = "wish_list")
public class WishList extends BaseEntity
{
    @Id
    @Column(name = "wish_list_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListUID;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_uid")
    private Product product;

    public WishList(User user, Product product)
    {
        this.user = user;
        this.product = product;
    }

    public void delete()
    {
        this.setIsDeleted(true);
    }
}
