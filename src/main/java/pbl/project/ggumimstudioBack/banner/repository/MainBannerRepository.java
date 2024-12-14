package pbl.project.ggumimstudioBack.banner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.banner.entity.MainBanner;

@Repository
public interface MainBannerRepository extends JpaRepository<MainBanner, Long>, MainBannerRepositoryCustom
{

}
