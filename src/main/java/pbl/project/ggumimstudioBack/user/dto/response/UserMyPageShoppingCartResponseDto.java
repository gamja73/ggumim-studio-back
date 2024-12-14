package pbl.project.ggumimstudioBack.user.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.user.entity.ShoppingCart;
import pbl.project.ggumimstudioBack.user.entity.WishList;

@Data
public class UserMyPageShoppingCartResponseDto
{
    private Long shoppingCartUID;
    private Long productUID;
    private String productMainImg;
    private String productName;
    private String productPrice;

    public UserMyPageShoppingCartResponseDto(ShoppingCart shoppingCart)
    {
        this.shoppingCartUID = shoppingCart.getShoppingCartUID();
        this.productUID = shoppingCart.getProduct().getProductUID();
        this.productMainImg = shoppingCart.getProduct().getProductMainImg();
        this.productName = shoppingCart.getProduct().getProductName();
        this.productPrice = CommonUtil.formatNumberWithComma(shoppingCart.getProduct().getProductPrice().intValue());
    }
}
