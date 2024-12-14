package pbl.project.ggumimstudioBack.user.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductListResponseDto;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageWishListResponseDto;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.entity.WishList;

import java.util.List;

import static pbl.project.ggumimstudioBack.product.entity.QProduct.product;
import static pbl.project.ggumimstudioBack.user.entity.QWishList.wishList;

@RequiredArgsConstructor
public class WishListRepositoryImpl implements WishListRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

    @Override
    public PaginationResponse<UserMyPageWishListResponseDto> myPageWishList(User user, BaseSearchParamRequestDto searchParam)
    {
        int skip = (searchParam.getPage() - 1) * searchParam.getLimit();

        List<WishList> query = queryFactory
                .selectFrom(wishList)
                .where(wishList.user.eq(user)
                        .and(wishList.isDeleted.eq(false)))
                .limit(searchParam.getLimit())
                .offset(skip)
                .orderBy(product.productUID.desc())
                .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(wishList)
                .where(wishList.user.eq(user)
                        .and(wishList.isDeleted.eq(false)))
                .fetchFirst();

        return PaginationResponse.<UserMyPageWishListResponseDto>builder()
                .totalCount(count.intValue())
                .currentPage(searchParam.getPage())
                .totalPages(count > 0 ? (int) Math.ceil((double) count / searchParam.getLimit()) : 1)
                .itemList(query.stream().map(UserMyPageWishListResponseDto::new).toList())
                .build();
    }

    @Override
    public WishList findProductWish(User target, Product wishProduct)
    {
        return queryFactory.selectFrom(wishList)
                .where(wishList.user.eq(target)
                        .and(wishList.product.eq(wishProduct))
                        .and(wishList.isDeleted.eq(false)))
                .fetchFirst();
    }
}
