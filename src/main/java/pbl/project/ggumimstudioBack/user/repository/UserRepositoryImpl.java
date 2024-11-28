package pbl.project.ggumimstudioBack.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import pbl.project.ggumimstudioBack.user.entity.User;

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

}
