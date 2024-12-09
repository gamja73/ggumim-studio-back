package pbl.project.ggumimstudioBack.user.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.dto.response.ProductListResponseDto;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.user.dto.response.AdminUserListResponseDto;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.util.List;

import static pbl.project.ggumimstudioBack.product.entity.QProduct.product;
import static pbl.project.ggumimstudioBack.user.entity.QUser.user;

@AllArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

    @Override
    public User findByUserID(String userID)
    {
        return queryFactory.selectFrom(user)
                .where(user.userId.eq(userID))
                .fetchFirst();
    }

    @Override
    public Boolean existsByUserId(String userId)
    {
        return queryFactory.selectFrom(user)
                .where(user.isDeleted.eq(false)
                        .and(user.userId.eq(userId)))
                .fetchFirst() != null;
    }

    @Override
    public Boolean existsByCallPhone(String callPhone)
    {
        return queryFactory.selectFrom(user)
                .where(user.isDeleted.eq(false)
                        .and(user.callPhone.eq(callPhone)))
                .fetchFirst() != null;
    }

    @Override
    public Boolean existsByEmail(String email)
    {
        return queryFactory.selectFrom(user)
                .where(user.isDeleted.eq(false)
                        .and(user.userEmail.eq(email)))
                .fetchFirst() != null;
    }

    @Override
    public PaginationResponse<AdminUserListResponseDto> getAdminUserList(BaseSearchParamRequestDto searchParam)
    {
        int skip = (searchParam.getPage() - 1) * searchParam.getLimit();

        List<User> query = queryFactory
                .selectFrom(user)
                .limit(searchParam.getLimit())
                .offset(skip)
                .orderBy(user.userUID.desc())
                .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(user)
                .fetchFirst();

        return PaginationResponse.<AdminUserListResponseDto>builder()
                .totalCount(count.intValue())
                .currentPage(searchParam.getPage())
                .totalPages(count > 0 ? (int) Math.ceil((double) count / searchParam.getLimit()) : 1)
                .itemList(query.stream().map(AdminUserListResponseDto::new).toList())
                .build();
    }
}
