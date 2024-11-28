package pbl.project.ggumimstudioBack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController
{
    private final JwtUtil jwtUtil;

    @GetMapping("/myPage")
    public CommonApiResponse<?> myPage()
    {
        return CommonApiResponse.OK();
    }
}
