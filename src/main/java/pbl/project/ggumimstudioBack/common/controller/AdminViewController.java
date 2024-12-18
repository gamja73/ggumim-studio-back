package pbl.project.ggumimstudioBack.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pbl.project.ggumimstudioBack.common.AdminService;
import pbl.project.ggumimstudioBack.common.dto.response.AdminDashBoardResponseDto;

@Controller
@RequestMapping("/admin")
public class AdminViewController
{

    private final AdminService adminService;

    public AdminViewController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    @GetMapping("")
    public String dashboard(Model model)
    {
        AdminDashBoardResponseDto pageData = adminService.getDashBoardData();

        model.addAttribute("pageData", pageData);

        return "dashBoard";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/table")
    public String tablePage()
    {
        return "table";
    }

    @GetMapping("/404")
    public String error404Page()
    {
        return "common/error/404";
    }
}
