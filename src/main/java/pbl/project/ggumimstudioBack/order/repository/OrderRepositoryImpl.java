package pbl.project.ggumimstudioBack.order.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.order.dto.response.AdminOrderListResponseDto;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static pbl.project.ggumimstudioBack.order.entity.QOrder.order;
import static pbl.project.ggumimstudioBack.order.entity.QOrderItem.orderItem;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

    @Override
    public PaginationResponse<AdminOrderListResponseDto> getUserOrderList(BaseSearchParamRequestDto searchParam, User user)
    {
        int skip = (searchParam.getPage() - 1) * searchParam.getLimit();

        JPAQuery<Order> query = queryFactory
                .selectFrom(order)
                .where(order.user.eq(user));

        List<Order> orderList = query.limit(searchParam.getLimit())
                .offset(skip)
                .orderBy(order.orderUID.desc())
                .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(order)
                .where(order.user.eq(user))
                .fetchFirst();

        return PaginationResponse.<AdminOrderListResponseDto>builder()
                .totalCount(count.intValue())
                .currentPage(searchParam.getPage())
                .totalPages(count > 0 ? (int) Math.ceil((double) count / searchParam.getLimit()) : 1)
                .itemList(orderList.stream().map(orderData -> {
                    List<OrderItem> orderItemList = queryFactory.selectFrom(orderItem).where(orderItem.order.eq(orderData)).fetch();
                    return new AdminOrderListResponseDto(orderData, orderItemList);
                }).toList())
                .build();
    }

    @Override
    public PaginationResponse<AdminOrderListResponseDto> getAdminOrderList(BaseSearchParamRequestDto searchParam)
    {
        int skip = (searchParam.getPage() - 1) * searchParam.getLimit();

        JPAQuery<Order> query = queryFactory
                .selectFrom(order)
                .where();

        List<Order> orderList = query.limit(searchParam.getLimit())
                .offset(skip)
                .orderBy(order.orderUID.desc())
                .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(order)
                .where()
                .fetchFirst();

        return PaginationResponse.<AdminOrderListResponseDto>builder()
            .totalCount(count.intValue())
            .currentPage(searchParam.getPage())
            .totalPages(count > 0 ? (int) Math.ceil((double) count / searchParam.getLimit()) : 1)
            .itemList(orderList.stream().map(orderData -> {
                List<OrderItem> orderItemList = queryFactory.selectFrom(orderItem).where(orderItem.order.eq(orderData)).fetch();
                return new AdminOrderListResponseDto(orderData, orderItemList);
            }).toList())
            .build();
    }

    @Override
    public List<Order> getDashBoardOrderMonth()
    {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.withDayOfMonth(1).atStartOfDay();;
        LocalDateTime endOfDay = today.atStartOfDay().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1).minusNanos(1);

        return queryFactory.selectFrom(order)
                .where(order.createdAt.between(startOfDay, endOfDay))
                .fetch();
    }

    @Override
    public List<Order> getDashBoardOrderDay()
    {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atStartOfDay().plusDays(1).minusNanos(1);

        return queryFactory.selectFrom(order)
                .where(order.createdAt.between(startOfDay, endOfDay))
                .fetch();
    }
}
