package pbl.project.ggumimstudioBack.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class AdminProductViewController
{
    @GetMapping("")
    public String createAdminProduct()
    {
        return "product/productCreateForm";
    }

    @GetMapping("/list")
    public String adminProductList()
    {
        return "product/productList";
    }
}
