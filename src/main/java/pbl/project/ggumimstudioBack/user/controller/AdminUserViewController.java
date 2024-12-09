package pbl.project.ggumimstudioBack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.user.dto.response.AdminUserDetailResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.AdminUserListResponseDto;
import pbl.project.ggumimstudioBack.user.service.AdminUserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminUserViewController
{
    private final AdminUserService adminUserService;

    @GetMapping("/{userUID}")
    public String adminProductDetail(@PathVariable("userUID") Long userUID, Model model)
    {
        AdminUserDetailResponseDto pageData = adminUserService.getAdminUserDetail(userUID);

        model.addAttribute("pageData", pageData);

        return "user/userDetail";
    }

    @GetMapping("/list")
    public String adminUserList(BaseSearchParamRequestDto searchParam, Model model)
    {
        PaginationResponse<AdminUserListResponseDto> pageData = adminUserService.getAdminUserList(searchParam);

        model.addAttribute("totalCount", pageData.getTotalCount());
        model.addAttribute("totalPages", pageData.getTotalPages());
        model.addAttribute("currentPage", pageData.getCurrentPage());
        model.addAttribute("currentGroupPage", (searchParam.getPage() - 1) / 5 + 1);
        model.addAttribute("paginationLimit", 5);
        model.addAttribute("page", searchParam.getPage());
        model.addAttribute("limit", searchParam.getLimit());
        model.addAttribute("searchType", searchParam.getSearchType());
        model.addAttribute("searchValue", searchParam.getSearchValue());
        model.addAttribute("startAt", searchParam.getStartAt());
        model.addAttribute("endAt", searchParam.getEndAt());

        model.addAttribute("pageData", pageData);
        model.addAttribute("pageUrl", "/admin/user/list");

        return "user/userList";
    }
}
