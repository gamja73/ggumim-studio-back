package pbl.project.ggumimstudioBack.user.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ProductShoppingCartListRequestDto
{
    private List<Long> productUIDList;
}
