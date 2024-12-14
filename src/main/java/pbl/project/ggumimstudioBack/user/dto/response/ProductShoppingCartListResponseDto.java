package pbl.project.ggumimstudioBack.user.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductShoppingCartListResponseDto
{
    private Integer success;
    private Integer fail;

    public ProductShoppingCartListResponseDto(Integer success, Integer fail)
    {
        this.success = success;
        this.fail = fail;
    }
}
