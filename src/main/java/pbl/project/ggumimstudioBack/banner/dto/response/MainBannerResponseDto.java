package pbl.project.ggumimstudioBack.banner.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.banner.entity.MainBanner;

@Data
public class MainBannerResponseDto
{
    private String bannerImg;
    private String bannerUrl;

    public MainBannerResponseDto(MainBanner mainBanner)
    {
        this.bannerImg = mainBanner.getMainBannerImg();
        this.bannerUrl = mainBanner.getMainBannerLink();
    }
}
