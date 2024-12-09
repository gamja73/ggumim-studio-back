package pbl.project.ggumimstudioBack.user.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.user.dto.response.AdminUserDetailResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.AdminUserListResponseDto;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminUserService
{
    private final UserRepository userRepository;

    public AdminUserDetailResponseDto getAdminUserDetail(Long userUID)
    {
        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return new AdminUserDetailResponseDto(target);
    }

    public PaginationResponse<AdminUserListResponseDto> getAdminUserList(BaseSearchParamRequestDto searchParam)
    {
        return userRepository.getAdminUserList(searchParam);
    }
}
