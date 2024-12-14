package pbl.project.ggumimstudioBack.user.repository;

import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageShoppingCartResponseDto;
import pbl.project.ggumimstudioBack.user.entity.ShoppingCart;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.util.List;

public interface ShoppingCartRepositoryCustom
{
    ShoppingCart findProductShoppingCart(User target, Product product);

    List<UserMyPageShoppingCartResponseDto> myPageShoppingCart(User target);
}
