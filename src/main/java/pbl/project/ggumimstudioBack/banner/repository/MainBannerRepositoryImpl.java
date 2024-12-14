package pbl.project.ggumimstudioBack.banner.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pbl.project.ggumimstudioBack.banner.dto.response.MainBannerResponseDto;
import pbl.project.ggumimstudioBack.banner.entity.MainBanner;

import java.util.List;

import static pbl.project.ggumimstudioBack.banner.entity.QMainBanner.mainBanner;

@RequiredArgsConstructor
public class MainBannerRepositoryImpl implements MainBannerRepositoryCustom
{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MainBannerResponseDto> getMainBannerList()
    {
        List<MainBanner> query = queryFactory.selectFrom(mainBanner)
                .where(mainBanner.isVisible.eq(true).and(mainBanner.isDeleted.eq(false)))
                .fetch();

        return query.stream().map(MainBannerResponseDto::new).toList();
    }
}
