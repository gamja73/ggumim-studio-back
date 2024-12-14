package pbl.project.ggumimstudioBack.user.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.user.entity.WishList;

@Data
public class UserMyPageWishListResponseDto
{
    private Long wishListUID;
    private Long productUID;
    private String productMainImg;
    private String productName;
    private String productPrice;

    public UserMyPageWishListResponseDto(WishList wishList)
    {
        this.wishListUID = wishList.getWishListUID();
        this.productUID = wishList.getProduct().getProductUID();
        this.productMainImg = wishList.getProduct().getProductMainImg();
        this.productName = wishList.getProduct().getProductName();
        this.productPrice = CommonUtil.formatNumberWithComma(wishList.getProduct().getProductPrice().intValue());
    }
}
