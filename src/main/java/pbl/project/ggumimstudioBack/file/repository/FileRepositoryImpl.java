package pbl.project.ggumimstudioBack.file.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileRepositoryImpl implements FileRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

}
