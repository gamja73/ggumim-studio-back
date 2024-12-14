package pbl.project.ggumimstudioBack.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom
{
    private final JPAQueryFactory queryFactory;
}
