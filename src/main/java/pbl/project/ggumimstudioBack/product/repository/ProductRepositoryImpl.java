package pbl.project.ggumimstudioBack.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

}
