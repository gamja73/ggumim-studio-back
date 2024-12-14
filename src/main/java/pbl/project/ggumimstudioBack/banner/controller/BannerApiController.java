package pbl.project.ggumimstudioBack.banner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pbl.project.ggumimstudioBack.banner.dto.response.MainBannerResponseDto;
import pbl.project.ggumimstudioBack.banner.service.BannerService;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banner")
public class BannerApiController
{
    private final BannerService bannerService;

    @GetMapping("/main")
    public CommonApiResponse<List<MainBannerResponseDto>> mainBanner()
    {
        return CommonApiResponse.OK(bannerService.getMainBanner());
    }
}
