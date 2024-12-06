package pbl.project.ggumimstudioBack.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController
{
    @GetMapping("")
    public String dashboard()
    {
        return "index";
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
