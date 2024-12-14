package pbl.project.ggumimstudioBack.banner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.banner.dto.response.MainBannerResponseDto;
import pbl.project.ggumimstudioBack.banner.repository.MainBannerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService
{

    private final MainBannerRepository mainBannerRepository;

    public List<MainBannerResponseDto> getMainBanner()
    {
        return mainBannerRepository.getMainBannerList();
    }
}
