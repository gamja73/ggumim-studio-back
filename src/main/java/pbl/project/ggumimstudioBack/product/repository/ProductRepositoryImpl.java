package pbl.project.ggumimstudioBack.product.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.dto.response.AdminProductListResponseDto;
import pbl.project.ggumimstudioBack.product.dto.response.ProductListResponseDto;
import pbl.project.ggumimstudioBack.product.entity.Product;

import java.util.List;

import static pbl.project.ggumimstudioBack.product.entity.QProduct.product;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

    @Override
    public PaginationResponse<AdminProductListResponseDto> getAdminProductList(BaseSearchParamRequestDto searchParam)
    {
        int skip = (searchParam.getPage() - 1) * searchParam.getLimit();

        List<Product> query = queryFactory
            .selectFrom(product)
            .limit(searchParam.getLimit())
            .offset(skip)
            .orderBy(product.productUID.desc())
            .fetch();

        Long count = queryFactory
            .select(Wildcard.count)
            .from(product)
            .fetchFirst();

        return PaginationResponse.<AdminProductListResponseDto>builder()
                .totalCount(count.intValue())
                .currentPage(searchParam.getPage())
                .totalPages(count > 0 ? (int) Math.ceil((double) count / searchParam.getLimit()) : 1)
                .itemList(query.stream().map(AdminProductListResponseDto::new).toList())
                .build();
    }

    @Override
    public PaginationResponse<ProductListResponseDto> getProductList(BaseSearchParamRequestDto searchParam)
    {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(product.isDeleted.eq(false).and(product.isExposure.eq(true)));

        int skip = (searchParam.getPage() - 1) * searchParam.getLimit();

        JPAQuery<Product> query = queryFactory
                .selectFrom(product)
                .where(builder);

        List<Product> productList = query.limit(searchParam.getLimit())
            .offset(skip)
            .orderBy(product.productUID.desc())
            .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(product)
                .where(builder)
                .fetchFirst();

        return PaginationResponse.<ProductListResponseDto>builder()
                .totalCount(count.intValue())
                .currentPage(searchParam.getPage())
                .totalPages(count > 0 ? (int) Math.ceil((double) count / searchParam.getLimit()) : 1)
                .itemList(query.stream().map(ProductListResponseDto::new).toList())
                .build();
    }
}
