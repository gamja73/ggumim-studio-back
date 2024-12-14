package pbl.project.ggumimstudioBack.banner.repository;

import pbl.project.ggumimstudioBack.banner.dto.response.MainBannerResponseDto;

import java.util.List;

public interface MainBannerRepositoryCustom
{
    List<MainBannerResponseDto> getMainBannerList();
}
