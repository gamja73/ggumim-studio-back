package pbl.project.ggumimstudioBack.user.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageShoppingCartResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageWishListResponseDto;
import pbl.project.ggumimstudioBack.user.entity.ShoppingCart;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.entity.WishList;

import java.util.List;

import static pbl.project.ggumimstudioBack.product.entity.QProduct.product;
import static pbl.project.ggumimstudioBack.user.entity.QShoppingCart.shoppingCart;
import static pbl.project.ggumimstudioBack.user.entity.QWishList.wishList;

@RequiredArgsConstructor
public class ShoppingCartRepositoryImpl implements ShoppingCartRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

    @Override
    public ShoppingCart findProductShoppingCart(User target, Product product)
    {
        return queryFactory.selectFrom(shoppingCart)
                .where(shoppingCart.user.eq(target)
                        .and(shoppingCart.product.eq(product))
                        .and(shoppingCart.isDeleted.eq(false)))
                .fetchFirst();
    }

    @Override
    public List<UserMyPageShoppingCartResponseDto> myPageShoppingCart(User target)
    {
        List<ShoppingCart> query = queryFactory
                .selectFrom(shoppingCart)
                .where(shoppingCart.user.eq(target)
                        .and(shoppingCart.isDeleted.eq(false)))
                .orderBy(product.productUID.desc())
                .fetch();

        return query.stream().map(UserMyPageShoppingCartResponseDto::new).toList();
    }
}
