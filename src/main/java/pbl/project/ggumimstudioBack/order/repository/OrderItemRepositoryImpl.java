package pbl.project.ggumimstudioBack.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom
{
    private final JPAQueryFactory queryFactory;
}
